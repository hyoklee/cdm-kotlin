// Generated by jextract

package com.sunya.netchdf.mfhdfClib.ffm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$78 {

    static final FunctionDescriptor adjtime$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle adjtime$MH = RuntimeHelper.downcallHandle(
        "adjtime",
        constants$78.adjtime$FUNC
    );
    static final FunctionDescriptor getitimer$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle getitimer$MH = RuntimeHelper.downcallHandle(
        "getitimer",
        constants$78.getitimer$FUNC
    );
    static final FunctionDescriptor setitimer$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle setitimer$MH = RuntimeHelper.downcallHandle(
        "setitimer",
        constants$78.setitimer$FUNC
    );
    static final FunctionDescriptor utimes$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle utimes$MH = RuntimeHelper.downcallHandle(
        "utimes",
        constants$78.utimes$FUNC
    );
    static final FunctionDescriptor lutimes$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle lutimes$MH = RuntimeHelper.downcallHandle(
        "lutimes",
        constants$78.lutimes$FUNC
    );
    static final FunctionDescriptor futimes$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle futimes$MH = RuntimeHelper.downcallHandle(
        "futimes",
        constants$78.futimes$FUNC
    );
}


