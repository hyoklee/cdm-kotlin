// Generated by jextract

package com.sunya.netchdf.hdf5Clib.ffm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public interface H5E_walk1_t {

    int apply(int n, java.lang.foreign.MemoryAddress err_desc, java.lang.foreign.MemoryAddress client_data);
    static MemorySegment allocate(H5E_walk1_t fi, MemorySession session) {
        return RuntimeHelper.upcallStub(H5E_walk1_t.class, fi, constants$107.H5E_walk1_t$FUNC, session);
    }
    static H5E_walk1_t ofAddress(MemoryAddress addr, MemorySession session) {
        MemorySegment symbol = MemorySegment.ofAddress(addr, 0, session);
        return (int _n, java.lang.foreign.MemoryAddress _err_desc, java.lang.foreign.MemoryAddress _client_data) -> {
            try {
                return (int)constants$107.H5E_walk1_t$MH.invokeExact((Addressable)symbol, _n, (java.lang.foreign.Addressable)_err_desc, (java.lang.foreign.Addressable)_client_data);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}


