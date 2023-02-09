package sunya.cdm.hdf5

import sunya.cdm.api.*
import sunya.cdm.hdf5.H5builder.Companion.HDF5_CLASS
import sunya.cdm.hdf5.H5builder.Companion.HDF5_DIMENSION_LIST
import sunya.cdm.hdf5.H5builder.Companion.HDF5_DIMENSION_NAME
import sunya.cdm.hdf5.H5builder.Companion.HDF5_DIMENSION_SCALE
import sunya.cdm.hdf5.H5builder.Companion.HDF5_REFERENCE_LIST
import sunya.cdm.iosp.*
import java.io.IOException

internal val includeOriginalAttributes = true
internal val debugDimensionScales = true

internal fun H5builder.buildCdm(h5root : H5Group) : Group {
    return buildGroup(h5root).build(null)
}

internal fun H5builder.buildGroup(group5 : H5Group) : Group.Builder {
    val builder = Group.Builder(group5.name)

    makeDimensions(builder, group5)

    group5.attributes().forEach { builder.addAttribute( buildAttribute( it )) }

    group5.variables.filter{ it.isVariable }.forEach { builder.addVariable( buildVariable( it )) }

    group5.nestedGroups.forEach { builder.addGroup( buildGroup( it )) }

    return builder
}

internal fun H5builder.buildAttribute(att5 : AttributeMessage) : Attribute {
    val h5type = H5Type(att5.mdt)
    val values = this.readAttributeData(att5, h5type)
    val useType = if (h5type.dataType == DataType.CHAR) DataType.STRING else h5type.dataType
    return Attribute(att5.name,  useType, values)
}

internal fun H5builder.buildVariable(v5 : H5Variable) : Variable.Builder {
    val builder = Variable.Builder()
    builder.name = v5.name
    builder.dataType = v5.dataType()
    for (att5 in v5.attributes()) {
        builder.attributes.add(buildAttribute(att5))
    }
    return builder
}

///////////////////////// Dimensions

/*
   * from https://www.unidata.ucar.edu/software/netcdf/docs/netcdf.html#NetCDF_002d4-Format
   * C.3.7 Attributes
   *
   * Attributes in HDF5 and netCDF-4 correspond very closely. Each attribute in an HDF5 file is represented as an
   * attribute in the netCDF-4 file, with the exception of the attributes below, which are ignored by the netCDF-4 API.
   *
   * _Netcdf4Coordinates An integer array containing the dimension IDs of a variable which is a multi-dimensional
   * coordinate variable.
   * _nc3_strict When this (scalar, H5T_NATIVE_INT) attribute exists in the root group of the HDF5 file, the netCDF API
   * will enforce the netCDF classic model on the data file.
   * REFERENCE_LIST This attribute is created and maintained by the HDF5 dimension scale API.
   * CLASS This attribute is created and maintained by the HDF5 dimension scale API.
   * DIMENSION_LIST This attribute is created and maintained by the HDF5 dimension scale API.
   * NAME This attribute is created and maintained by the HDF5 dimension scale API.
   *
   * ----------
   * from dim_scales_wk9 - Nunes.ppt
   *
   * Attribute named "CLASS" with the value "DIMENSION_SCALE"
   * Optional attribute named "NAME"
   * Attribute references to any associated Dataset
   *
   * -------------
   * from https://www.unidata.ucar.edu/mailing_lists/archives/netcdfgroup/2008/msg00093.html
   *
   * Then comes the part you will have to do for your datasets. You open the data
   * dataset, get an ID, DID variable here, open the latitude dataset, get its ID,
   * DSID variable here, and "link" the 2 with this call
   *
   * if (H5DSattach_scale(did,dsid,DIM0) < 0)
   *
   * what this function does is to associated the dataset DSID (latitude) with the
   * dimension* specified by the parameter DIM0 (0, in this case, the first
   * dimension of the 2D array) of the dataset DID
   *
   * If you open HDF Explorer and expand the attributes of the "data" dataset you
   * will see an attribute called DIMENSION_LIST.
   * This is done by this function. It is an array that contains 2 HDF5 references,
   * one for the latitude dataset, other for the longitude)
   *
   * If you expand the "lat" dataset , you will see that it contains an attribute
   * called REFERENCE_LIST. It is a compound type that contains
   * 1) a reference to my "data" dataset
   * 2) the index of the data dataset this scale is to be associated with (0 for the lat, 1 for the lon)
   */

// find dimensions in h5group, add them to parentGroup
internal fun H5builder.makeDimensions(parentGroup: Group.Builder, h5group: H5Group) {

    // 1. find all objects with all CLASS = "DIMENSION_SCALE", make into a dimension. use shape(0) as length.
    h5group.variables.forEach { findDimensionScales(parentGroup, h5group, it) }

    // 2. if also a variable (NAME != "This is a ...") then first dim = itself, second matches length, if multiple
    // match, use :_Netcdf4Coordinates = 0, 3 and order of dimensions.
    h5group.variables.filter { it.is2DCoordinate }.forEach { findDimensionScales2D(h5group, it) }

    // 3. use DIMENSION_LIST to assign dimensions to other variables.
    h5group.variables.forEach { findSharedDimensions(parentGroup, h5group, it) }

    for (d in h5group.dimList) {
        parentGroup.addDimensionIfNotExists(d)
    }
}

// find the Dimension Scale objects, turn them into shared dimensions
// always has attribute CLASS = "DIMENSION_SCALE"
// note that we dont bother looking at their REFERENCE_LIST
@Throws(IOException::class)
internal fun H5builder.findDimensionScales(g: Group.Builder, h5group: H5Group, h5variable: H5Variable) {

    val iter = h5variable.attributes().iterator()
    while (iter.hasNext()) {
        val matt: AttributeMessage = iter.next()
        if (matt.name == HDF5_CLASS) {
            val att: Attribute = buildAttribute(matt)
            check(att.isString)
            val value: String = att.values[0] as String
            if (value == HDF5_DIMENSION_SCALE && h5variable.mds.rank() > 0) {
                // create a dimension - always use the first dataspace length
                h5variable.dimList = addDimension(
                    g,
                    h5group,
                    h5variable.name,
                    h5variable.mds.dims[0],
                    false // LOOK support isUnlimited?
                )
                h5variable.hasNetcdfDimensions = true
                if (!includeOriginalAttributes) {
                    iter.remove()
                }
                if (h5variable.mds.rank() > 1) {
                    h5variable.is2DCoordinate = true
                }
            }
        }
    }
}

// add a dimension, return its name
private fun addDimension(
    parent: Group.Builder,
    h5group: H5Group,
    name: String,
    length: Int,
    isUnlimited: Boolean
): String {
    val pos = name.lastIndexOf('/')
    val dimName = if (pos >= 0) name.substring(pos + 1) else name
    var d = h5group.dimMap[dimName] // first look in current group
    if (d == null) { // create if not found
        d = Dimension(name, length, isUnlimited)
        h5group.dimMap[dimName] = d
        h5group.dimList.add(d)
        parent.addDimension(d)
        if (debugDimensionScales) {
            println("addDimension name=" + name + " dim= " + d + " to group " + parent.name)
        }
    } else { // check has correct length
        check(d.length == length) { "addDimension: DimScale has different length than dimension it references dimScale=$dimName" }
    }
    return d.name
}

internal fun H5builder.findDimensionScales2D(h5group: H5Group, h5variable: H5Variable) {
    val lens: IntArray = h5variable.mds.dims
    if (lens.size > 2) {
        println("DIMENSION_LIST: dimension scale > 2 = ${h5variable.name}")
        return
    }

    // first dimension is itself
    val name: String = h5variable.name
    val pos = name.lastIndexOf('/')
    val dimName = if (pos >= 0) name.substring(pos + 1) else name
    val sbuff = StringBuilder()
    sbuff.append(dimName)
    sbuff.append(" ")

    // second dimension is really an anonymous dimension, ironically now we go through amazing hoops to keep it shared
    // 1. use dimids if they exist
    // 2. if length matches and unique, use it
    // 3. if no length matches or multiple matches, then use anonymous
    val want_len = lens[1] // second dimension
    var match: Dimension? = null
    var unique = true
    for (d in h5group.dimList) {
        if (d.length == want_len) {
            if (match == null) {
                match = d
            } else {
                unique = false
            }
        }
    }
    if (match != null && unique) {
        sbuff.append(match.name) // 2. if length matches and unique, use it
    } else {
        if (match == null) { // 3. if no length matches or multiple matches, then use anonymous
            println("DIMENSION_LIST: dimension scale ${h5variable.name} has second dimension ${want_len} but no match")
            sbuff.append(want_len)
        } else {
            println("DIMENSION_LIST: dimension scale ${h5variable.name} has second dimension ${want_len} but multiple matches")
            sbuff.append(want_len)
        }
    }
    h5variable.dimList = sbuff.toString()
}

// look for references to dimension scales, ie the variables that use them
// return true if this variable is compatible with netcdf4 data model
// LOOK WTF ??
@Throws(IOException::class)
internal fun H5builder.findSharedDimensions(g: Group.Builder, h5group: H5Group, h5variable: H5Variable): Boolean {

    val iter = h5variable.attributes().iterator()
    while (iter.hasNext()) {
        val matt: AttributeMessage = iter.next()
        when (matt.name) {
            HDF5_DIMENSION_LIST -> {
                // references : may extend the dimension rank?
                val att: Attribute = buildAttribute(matt) // this reads in the data
                if (att.values.size !=  h5variable.mds.rank()) {
                    // some attempts to writing hdf5 directly fail here
                    println("DIMENSION_LIST: must have same number of dimension scales as dimensions att=${att} on variable ${h5variable.name}")
                } else {
                    val sbuff = StringBuilder()
                    var i = 0
                    while (i < att.values.size) {
                        val name: String = att.values[i] as String // LOOK
                        // val dimName: String = extendDimension(g, h5group, name, mds.dims[i])
                        sbuff.append(name).append(" ")
                        i++
                    }
                    h5variable.dimList = sbuff.toString()
                    h5variable.hasNetcdfDimensions = true
                    if (debugDimensionScales) {
                        println("Found dimList '${h5variable.dimList}' for var '${h5variable.name}' dataType = ${h5variable.dataType()} matt={$matt}")
                    }
                    if (!includeOriginalAttributes) iter.remove()
                }
            }

            HDF5_DIMENSION_NAME -> {
                val att: Attribute = buildAttribute(matt)
                val value: String = att.values[0] as String
                if (value.startsWith("This is a netCDF dimension but not a netCDF variable")) {
                    h5variable.isVariable = false
                    isNetcdf4 = true
                }
                if (!includeOriginalAttributes) iter.remove()
                if (debugDimensionScales) {
                    println("Found $value")
                }
            }

            HDF5_REFERENCE_LIST -> if (!includeOriginalAttributes) iter.remove()
        }
    }

    return h5variable.hasNetcdfDimensions || h5variable.mds.rank() == 0
}

/* look for unlimited dimensions without dimension scale - must get length from the variable
internal fun H5builder.extendDimension(parent: Group.Builder, h5group: H5Group, name: String, length: Int): String {
    val pos = name.lastIndexOf('/')
    val dimName = if (pos >= 0) name.substring(pos + 1) else name
    var d = h5group.dimMap[dimName] // first look in current group
    if (d == null) {
        d = parent.findDimension(dimName).orElse(null) // then look in parent groups
    }
    if (d != null) {
        if (d.isUnlimited && length > d.length) {
            parent.replaceDimension(d.toBuilder().setLength(length).build())
        }
        check(!d.isUnlimited || length != d.length) { "extendDimension: DimScale has different length than dimension it references dimScale=$dimName" }
        return d.name
    }
    return dimName
}

 */


