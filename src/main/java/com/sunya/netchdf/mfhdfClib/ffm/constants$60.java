// Generated by jextract

package com.sunya.netchdf.mfhdfClib.ffm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$60 {

    static final FunctionDescriptor dup2$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle dup2$MH = RuntimeHelper.downcallHandle(
        "dup2",
        constants$60.dup2$FUNC
    );
    static final  OfAddress __environ$LAYOUT = Constants$root.C_POINTER$LAYOUT;
    static final VarHandle __environ$VH = constants$60.__environ$LAYOUT.varHandle();
    static final MemorySegment __environ$SEGMENT = RuntimeHelper.lookupGlobalVariable("__environ", constants$60.__environ$LAYOUT);
    static final FunctionDescriptor execve$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle execve$MH = RuntimeHelper.downcallHandle(
        "execve",
        constants$60.execve$FUNC
    );
    static final FunctionDescriptor fexecve$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle fexecve$MH = RuntimeHelper.downcallHandle(
        "fexecve",
        constants$60.fexecve$FUNC
    );
    static final FunctionDescriptor execv$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle execv$MH = RuntimeHelper.downcallHandle(
        "execv",
        constants$60.execv$FUNC
    );
    static final FunctionDescriptor execle$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle execle$MH = RuntimeHelper.downcallHandleVariadic(
        "execle",
        constants$60.execle$FUNC
    );
}


