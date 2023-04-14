package com.sunya.netchdf

import com.google.common.util.concurrent.AtomicDouble
import com.sunya.cdm.api.*
import com.sunya.cdm.api.Section.Companion.computeSize
import com.sunya.cdm.api.Section.Companion.equivalent
import com.sunya.cdm.array.*
import com.sunya.cdm.util.Stats
import com.sunya.cdm.util.nearlyEquals
import com.sunya.netchdf.hdf4Clib.Hdf4ClibFile
import com.sunya.netchdf.hdf5Clib.Hdf5ClibFile
import com.sunya.netchdf.netcdfClib.NClibFile
import com.sunya.testdata.*
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*
import java.util.stream.Stream
import kotlin.system.measureNanoTime
import kotlin.test.assertEquals
import kotlin.test.assertTrue

// Compare Netchdf against NetcdfClibFile / Hdf5ClibFile / Hdf4ClibFile
class NetchdfClibTest {

    companion object {
        @JvmStatic
        fun params(): Stream<Arguments> {
            // return NppFiles.params()
            return Stream.of( N3Files.params(), N4Files.params(), H5Files.params(), NetchdfExtraFiles.params(true), H4Files.params()).flatMap { i -> i };
        }

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            Stats.clear() // problem with concurrent tests
        }

        @JvmStatic
        @AfterAll
        fun afterAll() {
            if (versions.size > 0) {
                versions.keys.forEach{ println("$it = ${versions[it]!!.size } files") }
            }
            Stats.show()
        }

        private val versions = mutableMapOf<String, MutableList<String>>()

        var compareMiddleSection = true
        var showDataRead = true
        var showData = false
        var showFailedData = true
        var showCdl = false
    }

    /* tst_grps
        Relies on vlen-3 and vlen-4 mdt hash NOT matching v1-2 hash, so added again.

    netcdf tst_grps {
        types:
        opaque(10) opaque-1 ;
        int(*) vlen-1 ;

        group: the_in_crowd {
            types:
            opaque(7) opaque-2 ;
            byte(*) vlen-2 ;
        }

        group: the_out_crowd {
            types:
            opaque(4) opaque-3 ;
            byte(*) vlen-3 ;

            group: the_confused_crowd {
            types:
            opaque(13) opaque-4 ;
            byte(*) vlen-4 ;
        }
        }
    }
     */
    @Test
    @Disabled
    fun tst_grps() {
        compareCdlWithClib(testData + "devcdm/netcdf4/tst_grps.nc4")
    }

    @Test
    @Disabled
    fun compoundAttributeTest() {
        compareCdlWithClib(testData + "cdmUnitTest/formats/netcdf4/compound-attribute-test.nc")
    }

    @Test
    fun testOneCdl() {
        val filename = testData + "netchdf/tomas/S3A_OL_CCDB_CHAR_AllFiles.20101019121929_1.nc4"
        /* see if it can be read through H5
        openNetchdfFile(filename).use { ncfile ->
            println("netchdf ${ncfile!!.type()} $filename ")
            println("${ncfile.cdl()} ")
        }
        // see if it can be read through N4C
        NetcdfClibFile(filename).use { ncfile ->
            println("NetcdfClibFile ${ncfile.type()} $filename ")
            println("${ncfile.cdl()} ")
        }
        Hdf5ClibFile(filename).use { ncfile ->
            println("Hdf5ClibFile ${ncfile.type()} $filename ")
            println("${ncfile.cdl()} ")
        } */
        compareCdlWithClib(filename)
    }

    @Test
    fun compareOneData() {
        val filename = testData + "cdmUnitTest/formats/hdf5/20130212_CN021_P3_222k_B02_WD7195FBPAT10231Nat_Nat_Std_CHTNWD_OP3_14.mip222k.oschp"
        // val filename = testData + "cdmUnitTest/formats/hdf5/superblockIsOffsetNPP.h5"
        //val filename = testData + "cdmUnitTest/formats/hdf5/wrf/wrf_input_par.h5"
        // compareCdlWithClib(filename)

        /* openNetchdfFile(filename).use { ncfile ->
            val v = ncfile!!.rootGroup().allVariables().find { it.fullname() == "/DATASET=INPUT/TIME_STAMP_000001/MU" }
            val mydata = ncfile.readArrayData(v!!, null)
            val section = Section("0:0, 6:13, 3:6)")
            val mysdata = mydata.section(section)
            println("netch section $section data=$mysdata")

            Hdf5ClibFile(filename).use { hcfile ->
                val v = hcfile.rootGroup().allVariables().find { it.fullname() == "/DATASET=INPUT/TIME_STAMP_000001/MU" }
                val ncdata = hcfile.readArrayData(v!!, null)
                assertTrue (ncdata.equals(mydata))

                val section = Section("0:0, 6:13, 3:6)")
                val ncsdata = ncdata.section(section)
                println("H5C section $section data=$ncsdata")
                assertTrue (ncsdata.equals(mysdata))
            }
        } */

        /* see if it can be read through N4C
        NetcdfClibFile(filename).use { ncfile ->
            println("${ncfile.type()} $filename ")
            openNetchdfFile(filename).use { netch ->
                compareNetcdfData(ncfile, netch!!, null, null)
            }
        } */
        compareDataWithClib(filename)
    }

    @Test
    fun missingChunks() {
        compareDataWithClib(
            testData + "cdmUnitTest/formats/netcdf4/files/xma022032.nc",
            "/xma/dialoop_back"
        )
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    @ParameterizedTest
    @MethodSource("params")
    fun checkVersion(filename: String) {
        openNetchdfFile(filename).use { ncfile ->
            if (ncfile == null) {
                println("Not a netchdf file=$filename ")
                return
            }
            println("${ncfile.type()} $filename ")
            val paths = versions.getOrPut(ncfile.type()) { mutableListOf() }
            paths.add(filename)
        }
    }

    @ParameterizedTest
    @MethodSource("params")
    fun testShowNetchdfHeader(filename: String) {
        showNetchdfHeader(filename, null)
    }


    @ParameterizedTest
    @MethodSource("params")
    fun testCdlWithClib(filename: String) {
        compareCdlWithClib(filename)
    }

    @ParameterizedTest
    @MethodSource("params")
    fun testReadNetchdfData(filename: String) {
        readNetchdfData(filename)
    }

    @ParameterizedTest
    @MethodSource("params")
    fun testCompareDataWithClib(filename: String) {
        compareDataWithClib(filename)
    }

    //@ParameterizedTest
    //@MethodSource("params")
    fun testIterateWithClib(filename: String) {
        compareIterateWithClib(filename)
    }

}

//////////////////////////////////////////////////////////////////////////////////////////////////////

fun showNetchdfHeader(filename: String, varname: String? = null, section: Section? = null, showCdl : Boolean = false) {
    println(filename)
    openNetchdfFile(filename).use { myfile ->
        if (myfile == null) {
            println("*** not a netchdf file = $filename")
            return
        }
        println(myfile.cdl())
    }
}

fun showNcHeader(filename: String, varname: String? = null, section: Section? = null, showCdl : Boolean = false) {
    println(filename)
    NClibFile(filename).use { ncfile ->
        println(ncfile.cdl())
    }
}

fun readNetchdfData(filename: String, varname: String? = null, section: Section? = null, showCdl : Boolean = false) {
    // println("=============================================================")
    openNetchdfFile(filename).use { myfile ->
        if (myfile == null) {
            println("*** not a netchdf file = $filename")
            return
        }
        println("--- ${myfile.type()} $filename ")
        readMyData(myfile,varname, section, showCdl)
    }
}

fun readNcData(filename: String, varname: String? = null, section: Section? = null, showCdl : Boolean = false) {
    NClibFile(filename).use { ncfile ->
        readMyData(ncfile, varname, section, showCdl)
    }
}

fun compareCdlWithClib(filename: String) {
    println("=================")
    openNetchdfFile(filename, false).use { netchdf ->
        if (netchdf == null) {
            println("*** not a netchdf file = $filename")
            return
        }
        println("${netchdf.type()} $filename ")
        println("\nnetchdf = ${netchdf.cdl()}")

        if (netchdf.type().contains("hdf4") || netchdf.type().contains("hdf-eos2")) {
            Hdf4ClibFile(filename).use { hcfile ->
                assertEquals(hcfile.cdl(), netchdf.cdl())
            }
        } else if (netchdf.type().contains("netcdf")) {
            NClibFile(filename).use { ncfile ->
                assertEquals(ncfile.cdl(), netchdf.cdl())
            }
        }  else if (netchdf.type().contains("hdf5") || netchdf.type().contains("hdf-eos5")) {
            Hdf5ClibFile(filename).use { ncfile ->
                assertEquals(ncfile.cdl(), netchdf.cdl())
            }
        } else {
            println("*** no c library to compare for $filename")
        }
    }
}

fun compareDataWithClib(filename: String, varname: String? = null, section: Section? = null) {
    println("=============================================================")
    openNetchdfFile(filename).use { netchdf ->
        if (netchdf == null) {
            println("*** not a netchdf file = $filename")
            return
        }
        println("${netchdf.type()} $filename ${"%.2f".format(netchdf.size / 1000.0 / 1000.0)} Mbytes")
        if (NetchdfClibTest.showCdl) println("\n${netchdf.cdl()}")

        if (netchdf.type().contains("hdf4")) {
            Hdf4ClibFile(filename).use { ncfile ->
                compareNetcdfData(netchdf, ncfile, varname, section)
            }
        } else if (netchdf.type().contains("netcdf")) {
            NClibFile(filename).use { ncfile ->
                compareNetcdfData(netchdf, ncfile, varname, section)
            }
        }  else if (netchdf.type().contains("hdf5") || netchdf.type().contains("hdf-eos5")) {
            Hdf5ClibFile(filename).use { ncfile ->
                compareNetcdfData(netchdf, ncfile, varname, section)
            }
        } else {
            println("*** no c library to compare for $filename")
        }
    }
}

fun compareIterateWithClib(filename: String, varname: String? = null, section: Section? = null) {
    println("=============================================================")
    openNetchdfFile(filename).use { netchdf ->
        if (netchdf == null) {
            println("*** not a netchdf file = $filename")
            return
        }
        println("${netchdf.type()} $filename ${"%.2f".format(netchdf.size / 1000.0 / 1000.0)} Mbytes")
        if (NetchdfClibTest.showCdl) println("\n${netchdf.cdl()}")

        if (netchdf.type().contains("hdf4")) {
            Hdf4ClibFile(filename).use { ncfile ->
                compareIterateNetchdf(netchdf, ncfile, varname, section) // LOOK should be compareIterateWithHC
            }
        } else if (netchdf.type().contains("netcdf")) {
            NClibFile(filename).use { ncfile ->
                compareIterateNetchdf(netchdf, ncfile, varname, section)
            }
        } else if (netchdf.type().contains("hdf5") || netchdf.type().contains("hdf-eos5")) {
            Hdf5ClibFile(filename).use { ncfile ->
                compareIterateNetchdf(netchdf, ncfile, varname, section)
            }
        } else {
            println("*** no c library to compare for $filename")
        }
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////
// just read data from myfile

fun readMyData(myfile: Netchdf, varname: String? = null, section: Section? = null, showCdl : Boolean = false) {

    if (showCdl) {
        println(myfile.cdl())
    }
    // println(myfile.rootGroup().allVariables().map { it.fullname() })
    if (varname != null) {
        val myvar = myfile.rootGroup().allVariables().find { it.fullname() == varname }
        if (myvar == null) {
            println("cant find $varname")
            return
        }
        readOneVar(myvar, myfile, section)
    } else {
        myfile.rootGroup().allVariables().forEach { it ->
            readOneVar(it, myfile, null)
        }
    }
}

const val maxBytes = 100_000_000

fun readOneVar(myvar: Variable, myfile: Netchdf, section: Section?) {

    val section = Section.fill(section, myvar.shape)
    val nbytes = section.size() * myvar.datatype.size
    if (nbytes > maxBytes) {
        if (NetchdfClibTest.showDataRead) println(" * ${myvar.fullname()} read too big: ${nbytes} > $maxBytes")
    } else {
        val mydata = myfile.readArrayData(myvar, section)
        if (NetchdfClibTest.showDataRead) println(" ${myvar.datatype} ${myvar.fullname()}${myvar.shape.contentToString()} = " +
                    "${mydata.shape.contentToString()} ${computeSize(mydata.shape)} elems" )
        if (myvar.datatype == Datatype.CHAR) {
            testCharShape(myvar.shape, mydata.shape)
        } else {
            assertTrue(myvar.shape.equivalent(mydata.shape), "variable ${myvar.name}")
        }
        if (NetchdfClibTest.showData) println(mydata)
    }

    if (myvar.nelems > 8 && myvar.datatype != Datatype.CHAR) {
        readMiddleSection(myfile, myvar, myvar.shape)
    }
}

fun testCharShape(want: IntArray, got: IntArray) {
    val org = want.equivalent(got)
    val removeLast = removeLast(want)
    val removeLastOk = removeLast.equivalent(got)
    assertTrue(org or removeLastOk)
}

fun removeLast(org: IntArray): IntArray {
    if (org.size < 1) return org
    return IntArray(org.size - 1) { org[it] }
}

fun readMiddleSection(myfile: Netchdf, myvar: Variable, shape: IntArray) {
    val orgSection = Section(shape)
    val middleRanges = orgSection.ranges.map { range ->
        if (range == null) throw RuntimeException("Range is null")
        if (range.length < 9) range
        else Range(range.first + range.length / 3, range.last - range.length / 3)
    }
    val middleSection = Section(middleRanges)
    val nbytes = middleSection.size() * myvar.datatype.size
    if (nbytes > maxBytes) {
        if (NetchdfClibTest.showDataRead) println("  * ${myvar.fullname()}[${middleSection}] read too big: ${nbytes} > $maxBytes")
        readMiddleSection(myfile, myvar, middleSection.shape)
        return
    }

    val mydata = myfile.readArrayData(myvar, middleSection)
    if (NetchdfClibTest.showDataRead) println("  ${myvar.fullname()}[$middleSection] = ${mydata.shape.contentToString()} ${computeSize(mydata.shape)} elems")
    if (myvar.datatype == Datatype.CHAR) {
        testCharShape(middleSection.shape, mydata.shape)
    } else {
        assertTrue(middleSection.shape.equivalent(mydata.shape), "variable ${myvar.name}")
    }
    if (NetchdfClibTest.showData) println(mydata)
}

//////////////////////////////////////////////////////////////////////////////////////
// compare data from two Netchdf files

fun compareNetcdfData(myfile: Netchdf, cfile: Netchdf, varname: String?, section: Section? = null) {
    if (varname != null) {
        val myvar = myfile.rootGroup().allVariables().find { it.fullname() == varname }
        if (myvar == null) {
            throw RuntimeException(" *** cant find myvar $varname")
        }
        val cvar = cfile.rootGroup().allVariables().find { it.fullname() == myvar.fullname() }
        if (cvar == null) {
            throw RuntimeException(" *** cant find cvar $varname")
        }
        compareOneVar(myvar, myfile, cvar, cfile, section)
    } else {
        myfile.rootGroup().allVariables().forEach { myvar ->
            val cvar = cfile.rootGroup().allVariables().find { it.fullname() == myvar.fullname() }
            if (cvar == null) {
                println(" *** cant find ${myvar.fullname()} in cfile")
            } else {
                compareOneVar(myvar, myfile, cvar, cfile, null)
            }
        }

        cfile.rootGroup().allVariables().forEach { cvar ->
            val myvar = myfile.rootGroup().allVariables().find { it.fullname() == cvar.fullname() }
            if (myvar == null) {
                println(" *** cant find ${cvar.fullname()} in myfile")
            }
        }
    }
}

fun compareOneVar(myvar: Variable, myfile: Netchdf, cvar : Variable, cfile: Netchdf, section: Section?) {
    val filledSection = Section.fill(section, myvar.shape)
    val nbytes = filledSection.size() * myvar.datatype.size
    if (nbytes > 100_000_000) {
        println(" * ${myvar.fullname()} read too big = ${nbytes}")
    } else {
        val mydata = myfile.readArrayData(myvar, filledSection)
        val ncdata = try {
            cfile.readArrayData(cvar, filledSection)
        } catch (e : Exception) {
            println(" *** FAIL cfile.readArrayData for variable = ${cvar.datatype} ${cvar.fullname()} ${cvar.dimensions.map { it.name }}")
            throw e
        }
        println(" ${myvar.datatype} ${myvar.fullname()}[${filledSection}] = ${computeSize(mydata.shape)} elems" )

        if (myvar.datatype == Datatype.CHAR) {
            compareCharData(myvar.fullname(), mydata, ncdata)
        } else {
            if (!ncdata.equals(mydata)) {
                println(" *** FAIL comparing data for variable = ${cvar.datatype} ${cvar.fullname()} ${cvar.dimensions.map { it.name }}")
                if (NetchdfClibTest.showFailedData) {
                    println("\n mydata = $mydata")
                    println(" cdata = $ncdata")
                } else {
                    println("\n countDifferences = ${ArrayTyped.countDiff(ncdata, mydata)}")
                }
                assertTrue(false, "variable ${myvar.fullname()}")
                return
            } else {
                if (NetchdfClibTest.showData) {
                    print(" ${cvar.cdl()}, ")
                    print("\n mydata = $mydata")
                    print(" cdata = $ncdata")
                }
            }
        }
    }
    if (NetchdfClibTest.compareMiddleSection && cvar.nelems > 8 && cvar.datatype != Datatype.CHAR) {
        compareMiddleSection(myfile, myvar, cfile, cvar, cvar.shape)
    }
}

fun compareMiddleSection(myfile: Netchdf, myvar: Variable, cfile: Netchdf, cvar: Variable, shape : IntArray) {
    val orgSection = Section(shape)
    val middleRanges = orgSection.ranges.map { range ->
        if (range == null) throw RuntimeException("Range is null")
        if (range.length < 9) range
        else Range(range.first + range.length / 3, range.last - range.length / 3)
    }
    val middleSection = Section(middleRanges)
    val nbytes = middleSection.size() * myvar.datatype.size
    if (nbytes > 100_000_000) {
        println("  * ${myvar.fullname()} read too big = ${nbytes}")
        compareMiddleSection(myfile, myvar, cfile, cvar, middleSection.shape)
        return
    }

    val mydata = myfile.readArrayData(myvar, middleSection)
    val ncdata = try {
        cfile.readArrayData(cvar, middleSection)
    } catch (e: Exception) {
        println(" *** FAIL compareMiddleSection data for variable = ${cvar.datatype} ${cvar.fullname()} ${cvar.dimensions.map { it.name }}")
        throw e
    }
    println("  ${myvar.fullname()}[$middleSection] = ${mydata.shape.contentToString()} ${Section.computeSize(mydata.shape)} elems")

    if (myvar.datatype == Datatype.CHAR) {
        compareCharData(myvar.fullname(), mydata, ncdata)
    } else {
        if (!ncdata.equals(mydata)) {
            println(" *** FAIL comparing middle section variable = ${cvar}")
            if (NetchdfClibTest.showFailedData) {
                println(" mydata = $mydata")
                println(" cdata = $ncdata")
            } else {
                println("\n countDifferences = ${ArrayTyped.countDiff(ncdata, mydata)}")
            }
            assertTrue(false, "variable ${myvar.name}")
            return
        }
    }
}
fun compareCharData(name : String, mydata: ArrayTyped<*>, ncdata: ArrayTyped<*>) {
    if (!ArrayTyped.valuesEqual(ncdata, mydata)) {
        println("   *** FAIL comparing char variable = ${name}")
        print("   ncdata = $ncdata")
        print("   mydata = $mydata")
    }
}

//////////////////////////////////////////////////////////////////////////////////////
// compare reading data chunkIterate API with two Netchdf

private const val debugIter = false

fun compareIterateNetchdf(myfile: Netchdf, cfile: Netchdf, varname: String?, section: Section? = null) {
    if (varname != null) {
        val myvar = myfile.rootGroup().allVariables().find { it.fullname() == varname }
        if (myvar == null) {
            println(" *** cant find myvar $varname")
            return
        }
        val cvar = cfile.rootGroup().allVariables().find { it.fullname() == myvar.fullname() }
        if (cvar == null) {
            throw RuntimeException(" *** cant find cvar $varname")
        }
        compareOneVarIterate(myvar, myfile, cvar, cfile, section)
    } else {
        myfile.rootGroup().allVariables().forEach { myvar ->
            val cvar = cfile.rootGroup().allVariables().find { it.fullname() == myvar.fullname() }
            if (cvar == null) {
                println(" *** cant find ${myvar.fullname()} in cfile")
            } else {
                compareOneVarIterate(myvar, myfile, cvar, cfile, null)
            }
        }
    }
}

fun compareOneVarIterate(myvar: Variable, myfile: Netchdf, cvar : Variable, cfile: Netchdf, section: Section?) {
    val sum = AtomicDouble()
    sum.set(0.0)
    var countChunks = 0
    val time1 = measureNanoTime {
        val chunkIter = myfile.chunkIterator(myvar)
        for (pair in chunkIter) {
            if (debugIter) println(" compareOneVarIterate myvar=${myvar.name} ${pair.section} = ${pair.array.shape.contentToString()}")
            sumValues(pair.array)
            countChunks++
        }
    }
    Stats.of("netchdf", myfile.location(), "chunk").accum(time1, countChunks)
    val sum1 = sum.get()

    sum.set(0.0)
    countChunks = 0
    val time2 = measureNanoTime {
        val chunkIter = cfile.chunkIterator(cvar)
        for (pair in chunkIter) {
            if (debugIter) println(" compareOneVarIterate cvar=${cvar.name} ${pair.section} = ${pair.array.shape.contentToString()}")
            sumValues(pair.array)
            countChunks++
        }
    }
    Stats.of("nclib", cfile.location(), "chunk").accum(time2, countChunks)
    val sum2 = sum.get()

    if (sum1.isFinite() && sum2.isFinite()) {
        assertTrue(nearlyEquals(sum1, sum2), "$sum1 != $sum2 sum2")
        // println("sum = $sum1")
    }
}

///////////////////////////////////////////////////////////
val sum = AtomicDouble()
fun sumValues(array : ArrayTyped<*>) {
    if (array is ArraySingle) {
        return // fillValue the same ??
    }
    // cant cast unsigned to Numbers
    val useArray = when (array.datatype) {
        Datatype.UBYTE -> ArrayByte(array.shape, (array as ArrayUByte).bb)
        Datatype.USHORT -> ArrayShort(array.shape, (array as ArrayUShort).bb)
        Datatype.UINT -> ArrayInt(array.shape, (array as ArrayUInt).bb)
        Datatype.ULONG -> ArrayLong(array.shape, (array as ArrayULong).bb)
        else -> array
    }

    if (useArray.datatype.isNumber) {
        for (value in useArray) {
            val number = (value as Number)
            val numberd: Double = number.toDouble()
            if (numberd.isFinite()) {
                sum.getAndAdd(numberd)
            }
        }
    }
}
