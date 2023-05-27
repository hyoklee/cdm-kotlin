package com.sunya.netchdf.netcdf3

import com.sunya.netchdf.compareNetcdfData
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import com.sunya.netchdf.netcdfClib.NClibFile
import com.sunya.testdata.N3Files
import com.sunya.testdata.testData
import java.util.*
import java.util.stream.Stream

// Compare data reading for the same file with Netcdf3File and NetcdfClibFile
class N3dataCompare {

    companion object {
        @JvmStatic
        fun params(): Stream<Arguments> {
            return N3Files.params()
        }
    }

    @Test
    fun cdf5() {
        val filename = testData + "recent/cdf5/jays_DOMAIN000.nc"
        readDataCompareNC(filename, null)
    }

    // netcdf3 /home/all/testdata/netchdf/csiro/sixCells.nc 1.80 Mbytes
    // float lat[0:1] = 2 elems
    // this is the case where vsize=20, but calculated=8.
    // the file looks like it did use 20 bytes, but hard to know as there's only 2 records.
    // Im going to remove it from the test files (placed in exclude)
    @Test
    fun calcRecordSize() {
        readDataCompareNC(testData + "netchdf/csiro/sixCellsc.nc", null) // , "cellId")
    }

    @Test
    fun vsizeCdf1() {
        readDataCompareNC(testData + "devcdm/netcdf3/tst_small_classic.nc", null) // , "cellId")
    }

    @Test
    fun vsizeCdf2() {
        readDataCompareNC(testData + "devcdm/netcdf3/tst_small_64bit.nc", null) // , "cellId")
    }

    @Test
    fun problem() {
        readDataCompareNC(testData + "devcdm/netcdf3/tst_ncml.nc", null)
    }

    @ParameterizedTest
    @MethodSource("params")
    fun readN3dataCompareNC(filename : String) {
        readDataCompareNC(filename, null)
    }

    fun readDataCompareNC(filename : String, varname : String?) {
        val myfile = Netcdf3File(filename)
        val ncfile = NClibFile(filename)
        println(filename)
        println(myfile.cdl())
        compareNetcdfData(myfile, ncfile, varname)
        myfile.close()
        ncfile.close()
    }

}