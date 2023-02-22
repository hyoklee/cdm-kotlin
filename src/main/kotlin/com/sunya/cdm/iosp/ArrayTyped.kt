package com.sunya.cdm.iosp

import com.sunya.cdm.api.Datatype
import com.sunya.cdm.api.Section.Companion.computeSize

abstract class ArrayTyped<T>(val shape : IntArray) : Iterable<T> {

    override fun toString(): String {
        return buildString {
            append("shape=${shape.contentToString()} data=")
            for (value in this){
                append("$value,")
            }
            append("\n")
        }
    }

    companion object {
        fun contentEquals(array1 : ArrayTyped<*>, array2 : ArrayTyped<*>) : Boolean {
            if (!array1.shape.contentEquals(array2.shape)) {
                return false
            }
            val iter1 = array1.iterator()
            val iter2 = array2.iterator()
            var count = 0
            while (iter1.hasNext() && iter2.hasNext()) {
                val v1 = iter1.next()
                val v2 = iter2.next()
                count++
                if (v1 != v2) {
                    return false
                }
            }
            return true
        }
    }
}

class ArraySingle<T>(shape : IntArray, val datatype : Datatype, val fillValue : T) : ArrayTyped<T>(shape) {
    val nelems = computeSize(shape)
    override fun iterator(): Iterator<T> = SingleIterator()
    private inner class SingleIterator : AbstractIterator<T>() {
        private var idx = 0
        override fun computeNext() = if (idx++ >= nelems) done() else setNext(fillValue)
    }

    override fun toString(): String {
        return buildString {
            append("shape=${shape.contentToString()} data= $fillValue")
        }
    }
}
