// Generated by jextract

package com.sunya.netchdf.hdf5Clib.ffm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public interface H5I_free_t {

    int apply(java.lang.foreign.MemoryAddress _x0);
    static MemorySegment allocate(H5I_free_t fi, MemorySession session) {
        return RuntimeHelper.upcallStub(H5I_free_t.class, fi, constants$4.H5I_free_t$FUNC, session);
    }
    static H5I_free_t ofAddress(MemoryAddress addr, MemorySession session) {
        MemorySegment symbol = MemorySegment.ofAddress(addr, 0, session);
        return (java.lang.foreign.MemoryAddress __x0) -> {
            try {
                return (int)constants$4.H5I_free_t$MH.invokeExact((Addressable)symbol, (java.lang.foreign.Addressable)__x0);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}


