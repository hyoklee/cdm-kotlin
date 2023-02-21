package com.sunya.netchdf.hdf5

import com.sunya.cdm.api.Section
import com.sunya.cdm.iosp.LayoutTiled
import com.sunya.cdm.iosp.OpenFile
import com.sunya.cdm.iosp.OpenFileState
import java.nio.ByteOrder


/**
 * This holds the chunked data storage.
 * level 1A
 * A B-tree, version 1, used for data (node type 1)
 *
 * Version 1 B-trees in HDF5 files an implementation of the B-link tree, in which the sibling nodes at a particular
 * level
 * in the tree are stored in a doubly-linked list
 * The B-link trees implemented by the file format contain one more key than the number of children.
 * In other words, each child pointer out of a B-tree node has a left key and a right key.
 * The pointers out of internal nodes point to sub-trees while the pointers out of leaf nodes point to symbol nodes and
 * raw data chunks. Aside from that difference, internal nodes and leaf nodes are identical.
 *
 * @see "http://www.hdfgroup.org/HDF5/doc/H5.format.html.Btrees"
 */
class DataBTree(
    h5: H5builder,
    rootNodeAddress: Long,
    varShape: IntArray,
    storageSize: IntArray,
    memTracker: MemTracker?
) {
    private val h5: H5builder
    private val raf: OpenFile
    private val memTracker: MemTracker?
    private val rootNodeAddress: Long
    private val tiling: Tiling
    private val ndimStorage: Int
    private val wantType: Int
    private var owner: Any? = null

    init {
        this.h5 = h5
        raf = h5.raf
        this.rootNodeAddress = rootNodeAddress
        tiling = Tiling(varShape, storageSize)
        ndimStorage = storageSize.size
        this.memTracker = memTracker
        wantType = 1
    }

    fun setOwner(owner: Any?) {
        this.owner = owner
    }

    // used by H5tiledLayoutBB
    fun getDataChunkIteratorFilter(want: Section): DataBTree.DataChunkIterator {
        return DataChunkIterator(want)
    }

    // used by H5tiledLayout
    fun getDataChunkIteratorNoFilter(want: Section, nChunkDim: Int): LayoutTiled.DataChunkIterator {
        return DataChunkIteratorNoFilter(want, nChunkDim)
    }

    // An Iterator over the DataChunks in the btree.
    // returns the actual data from the btree leaf (level 0) nodes.
    // used by H5tiledLayout, when there are no filters
    internal inner class DataChunkIteratorNoFilter(want: Section, private val nChunkDim: Int) :
        LayoutTiled.DataChunkIterator {
        private val root: DataBTree.Node

        /**
         * Constructor
         *
         * @param want skip any nodes that are before this section
         * @param nChunkDim number of chunk dimensions - may be less than the offset[] length
         * @throws IOException on error
         */
        init {
            root = Node(rootNodeAddress, -1) // should we cache the nodes ???
            root.first(want.origin)
        }

        override operator fun hasNext(): Boolean {
            return root.hasNext() // && !node.greaterThan(wantOrigin);
        }

        override operator fun next(): LayoutTiled.DataChunk {
            val dataChunk: DataBTree.DataChunk = root.next()
            var offset: IntArray = dataChunk.offset
            if (offset.size > nChunkDim) { // may have to eliminate last offset
                offset = IntArray(nChunkDim)
                System.arraycopy(dataChunk.offset, 0, offset, 0, nChunkDim)
            }
            if (debugChunkOrder) System.out.printf("LayoutTiled.DataChunk next order %d%n", tiling.order(dataChunk.offset))
            return LayoutTiled.DataChunk(offset, dataChunk.filePos)
        }
    }

    // An Iterator over the DataChunks in the btree.
    // returns the data chunck info from the btree leaf (level 0) nodes
    // used by H5tiledLayoutBB, when there are filters
    inner class DataChunkIterator internal constructor(want: Section) {
        private val root: DataBTree.Node
        private val wantOrigin: IntArray

        /**
         * Constructor
         *
         * @param want skip any nodes that are before this section
         * @throws IOException on error
         */
        init {
            root = Node(rootNodeAddress, -1) // should we cache the nodes ???
            wantOrigin = want.origin
            root.first(wantOrigin)
        }

        operator fun hasNext(): Boolean {
            return root.hasNext() // && !node.greaterThan(wantOrigin);
        }

        operator fun next(): DataBTree.DataChunk {
            return root.next()
        }
    }

    // Btree nodes
    internal inner class Node(address: Long, parent: Long) {
        private val address: Long
        private val level: Int
        private val nentries: Int
        private var currentNode: DataBTree.Node? = null

        // level 0 only
        private val myEntries = mutableListOf<DataBTree.DataChunk>()

        // level > 0 only
        private val offsets = mutableListOf<IntArray>() // int[nentries][ndim]; // other levels

        // "For raw data chunk nodes, the child pointer is the address of a single raw data chunk"
        private val childPointer = mutableListOf<Long>() // long[nentries];

        private var currentEntry = 0 // track iteration; TODO why not an iterator ??

        init {
            if (debugDataBtree) debugOut.println("\n--> DataBTree read tree at address=$address parent= $parent owner= $owner")

            val state = OpenFileState(h5.getFileOffset(address), ByteOrder.LITTLE_ENDIAN)
            this.address = address
            val magic: String = raf.readString(state, 4)
            check(magic == "TREE") { "DataBTree doesnt start with TREE" }

            val type: Int = raf.readByte(state).toInt()
            check(type == wantType) { "DataBTree must be type $wantType" }

            level = raf.readByte(state).toInt()
            nentries = raf.readShort(state).toInt()
            val size: Long =
                8 + 2 * h5.sizeOffsets + nentries.toLong() * (8 + h5.sizeOffsets + 8 + ndimStorage)
            memTracker?.addByLen("Data BTree ($owner)", address, size)
            val leftAddress: Long = h5.readOffset(state)
            val rightAddress: Long = h5.readOffset(state)

            if (debugDataBtree) {
                debugOut.println("    type=$type level=$level nentries=$nentries size = $size")
                debugOut.println(
                    "    leftAddress=" + leftAddress + " =0x" + java.lang.Long.toHexString(leftAddress) + " rightAddress="
                            + rightAddress + " =0x" + java.lang.Long.toHexString(rightAddress)
                )
            }

            if (level == 0) {
                // read all entries as a DataChunk
                for (i in 0..nentries) {
                    val dc: DataBTree.DataChunk = DataChunk(state, ndimStorage, i == nentries)
                    myEntries.add(dc)
                    if (debugDataChunk) debugOut.println(dc)
                }

            } else { // just track the offsets and node addresses
                for (i in 0..nentries) {
                    state.pos += 8 // skip size, filterMask
                    val inner = IntArray(ndimStorage) { j ->
                        val loffset: Long = raf.readLong(state)
                        require(loffset < Int.MAX_VALUE) // why?
                        loffset.toInt()
                    }
                    offsets.add(inner) // LOOK not used ??
                    childPointer.add(if (i == nentries) -1 else h5.readOffset(state))
                }
            }
        }

        // this finds the first entry we dont want to skip.
        // entry i goes from [offset(i),offset(i+1))
        // we want to skip any entries we dont need, namely those where want >= offset(i+1)
        // so keep skipping until want < offset(i+1)
        fun first(wantOrigin: IntArray?) {
            if (debugChunkOrder && wantOrigin != null) System.out.printf(
                "Level %d: Tile want %d%n",
                level,
                tiling.order(wantOrigin)
            )
            if (level == 0) {
                // note nentries-1 - assume dont skip the last one
                currentEntry = 0
                while (currentEntry < nentries - 1) {
                    val entry: DataBTree.DataChunk = myEntries[currentEntry + 1] // look at the next one
                    if (debugChunkOrder) System.out.printf(" Entry=%d: Tile ending order= %d%n", currentEntry, tiling.order(entry.offset))
                    if (wantOrigin == null || tiling.compare(wantOrigin, entry.offset) < 0) break
                    currentEntry++
                }
                if (debugChunkOrder) System.out.printf("Level %d use entry= %d%n", level, currentEntry)
            } else {
                currentNode = null
                currentEntry = 0
                while (currentEntry < nentries) {
                    if (wantOrigin == null || tiling.compare(wantOrigin, offsets[currentEntry + 1]) < 0) {
                        currentNode = Node(childPointer[currentEntry], address)
                        if (debugChunkOrder) System.out.printf("Level %d use entry= %d%n", level, currentEntry)
                        currentNode!!.first(wantOrigin)
                        break
                    }
                    currentEntry++
                }

                // heres the case where its the last entry we want; the tiling.compare() above may fail
                if (currentNode == null) {
                    currentEntry = nentries - 1
                    currentNode = Node(childPointer[currentEntry], address)
                    currentNode!!.first(wantOrigin)
                }
            }
            require(nentries == 0 || currentEntry < nentries) { "$currentEntry >= $nentries" }
        }

        // TODO - wouldnt be a bad idea to terminate if possible instead of running through all subsequent entries
        operator fun hasNext(): Boolean {
            return if (level == 0) {
                currentEntry < nentries
            } else {
                if (currentNode!!.hasNext()) true else currentEntry < nentries - 1
            }
        }

        operator fun next(): DataBTree.DataChunk {
            return if (level == 0) {
                myEntries[currentEntry++]
            } else {
                if (currentNode!!.hasNext()) return currentNode!!.next()
                currentEntry++
                currentNode = Node(childPointer[currentEntry], address)
                currentNode!!.first(null)
                currentNode!!.next()
            }
        }
    }

    // these are part of the level 1A data structure, type 1
    // see http://www.hdfgroup.org/HDF5/doc/H5.format.html#V1Btrees,
    // see "Key" field (type 1) p 10
    // this is only for leaf nodes (level 0)
    inner class DataChunk internal constructor(state : OpenFileState, ndim: Int, last: Boolean) {
        val size : Int // size of chunk in bytes; need storage layout dimensions to interpret
        val filterMask : Int // bitfield indicating which filters have been skipped for this chunk
        val offset : IntArray// offset index of this chunk, reletive to entire array
        val filePos : Long // filePos of a single raw data chunk, already shifted by the offset if needed

        init {
            size = raf.readInt(state)
            filterMask = raf.readInt(state)
            offset = IntArray(ndim)
            for (i in 0 until ndim) {
                val loffset: Long = raf.readLong(state)
                require(loffset < Int.MAX_VALUE)
                offset[i] = loffset.toInt()
            }
            filePos = if (last) -1 else h5.readAddress(state)
            memTracker?.addByLen("Chunked Data ($owner)", filePos, size.toLong())
        }

        override fun toString(): String {
            val sbuff = StringBuilder()
            sbuff.append("  ChunkedDataNode size=").append(size).append(" filterMask=").append(filterMask)
                .append(" filePos=")
                .append(filePos).append(" offsets= ")
            for (anOffset in offset) sbuff.append(anOffset).append(" ")
            return sbuff.toString()
        }
    }

    companion object {
        private const val debugDataBtree = false
        private const val debugDataChunk = false
        private const val debugChunkOrder = false
        private val debugOut = System.out
    }
}