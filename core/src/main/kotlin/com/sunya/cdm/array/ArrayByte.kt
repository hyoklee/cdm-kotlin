package com.sunya.cdm.array

import com.sunya.cdm.api.Datatype
import java.nio.ByteBuffer

class ArrayByte(shape : IntArray, val values : ByteBuffer) : ArrayTyped<Byte>(Datatype.BYTE, shape) {

    override fun iterator(): Iterator<Byte> = BufferIterator()
    private inner class BufferIterator : AbstractIterator<Byte>() {
        private var idx = 0
        override fun computeNext() = if (idx >= values.limit()) done() else setNext(values[idx++])
    }

}