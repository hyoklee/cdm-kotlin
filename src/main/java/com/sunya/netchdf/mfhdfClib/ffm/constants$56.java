// Generated by jextract

package com.sunya.netchdf.mfhdfClib.ffm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$56 {

    static final FunctionDescriptor access$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle access$MH = RuntimeHelper.downcallHandle(
        "access",
        constants$56.access$FUNC
    );
    static final FunctionDescriptor faccessat$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle faccessat$MH = RuntimeHelper.downcallHandle(
        "faccessat",
        constants$56.faccessat$FUNC
    );
    static final FunctionDescriptor lseek$FUNC = FunctionDescriptor.of(Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle lseek$MH = RuntimeHelper.downcallHandle(
        "lseek",
        constants$56.lseek$FUNC
    );
    static final FunctionDescriptor close$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle close$MH = RuntimeHelper.downcallHandle(
        "close",
        constants$56.close$FUNC
    );
    static final FunctionDescriptor closefrom$FUNC = FunctionDescriptor.ofVoid(
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle closefrom$MH = RuntimeHelper.downcallHandle(
        "closefrom",
        constants$56.closefrom$FUNC
    );
    static final FunctionDescriptor read$FUNC = FunctionDescriptor.of(Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle read$MH = RuntimeHelper.downcallHandle(
        "read",
        constants$56.read$FUNC
    );
}


