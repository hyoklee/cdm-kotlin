// Generated by jextract

package com.sunya.netchdf.hdf5Clib.ffm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public interface H5P_prp_create_func_t {

    int apply(java.lang.foreign.MemoryAddress _x0, long _x1, java.lang.foreign.MemoryAddress _x2);
    static MemorySegment allocate(H5P_prp_create_func_t fi, MemorySession session) {
        return RuntimeHelper.upcallStub(H5P_prp_create_func_t.class, fi, constants$128.H5P_prp_create_func_t$FUNC, session);
    }
    static H5P_prp_create_func_t ofAddress(MemoryAddress addr, MemorySession session) {
        MemorySegment symbol = MemorySegment.ofAddress(addr, 0, session);
        return (java.lang.foreign.MemoryAddress __x0, long __x1, java.lang.foreign.MemoryAddress __x2) -> {
            try {
                return (int)constants$129.H5P_prp_create_func_t$MH.invokeExact((Addressable)symbol, (java.lang.foreign.Addressable)__x0, __x1, (java.lang.foreign.Addressable)__x2);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}


