// Generated by jextract

package com.sunya.netchdf.mfhdfClib.ffm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$73 {

    static final FunctionDescriptor getpagesize$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT);
    static final MethodHandle getpagesize$MH = RuntimeHelper.downcallHandle(
        "getpagesize",
        constants$73.getpagesize$FUNC
    );
    static final FunctionDescriptor getdtablesize$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT);
    static final MethodHandle getdtablesize$MH = RuntimeHelper.downcallHandle(
        "getdtablesize",
        constants$73.getdtablesize$FUNC
    );
    static final FunctionDescriptor truncate$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle truncate$MH = RuntimeHelper.downcallHandle(
        "truncate",
        constants$73.truncate$FUNC
    );
    static final FunctionDescriptor ftruncate$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle ftruncate$MH = RuntimeHelper.downcallHandle(
        "ftruncate",
        constants$73.ftruncate$FUNC
    );
    static final FunctionDescriptor brk$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle brk$MH = RuntimeHelper.downcallHandle(
        "brk",
        constants$73.brk$FUNC
    );
    static final FunctionDescriptor sbrk$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle sbrk$MH = RuntimeHelper.downcallHandle(
        "sbrk",
        constants$73.sbrk$FUNC
    );
}


