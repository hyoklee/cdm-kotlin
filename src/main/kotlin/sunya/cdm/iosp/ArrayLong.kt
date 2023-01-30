package sunya.cdm.iosp

import java.nio.LongBuffer

class ArrayLong(val values : LongBuffer, val shape : IntArray) : ArrayTyped<Long>() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ArrayLong

        if (values != other.values) return false
        if (!shape.contentEquals(other.shape)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = values.hashCode()
        result = 31 * result + shape.contentHashCode()
        return result
    }

    override fun toString(): String {
        return buildString {
            append("shape=${shape.contentToString()})\n")
            for (i in 0 until values.limit()) { append("${values[i]},")}
            append("\n")
        }
    }
}