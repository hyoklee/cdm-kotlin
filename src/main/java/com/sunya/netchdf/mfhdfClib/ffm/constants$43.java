// Generated by jextract

package com.sunya.netchdf.mfhdfClib.ffm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$43 {

    static final FunctionDescriptor strdup$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle strdup$MH = RuntimeHelper.downcallHandle(
        "strdup",
        constants$43.strdup$FUNC
    );
    static final FunctionDescriptor strndup$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle strndup$MH = RuntimeHelper.downcallHandle(
        "strndup",
        constants$43.strndup$FUNC
    );
    static final FunctionDescriptor strchr$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle strchr$MH = RuntimeHelper.downcallHandle(
        "strchr",
        constants$43.strchr$FUNC
    );
    static final FunctionDescriptor strrchr$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle strrchr$MH = RuntimeHelper.downcallHandle(
        "strrchr",
        constants$43.strrchr$FUNC
    );
    static final FunctionDescriptor strcspn$FUNC = FunctionDescriptor.of(Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle strcspn$MH = RuntimeHelper.downcallHandle(
        "strcspn",
        constants$43.strcspn$FUNC
    );
    static final FunctionDescriptor strspn$FUNC = FunctionDescriptor.of(Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle strspn$MH = RuntimeHelper.downcallHandle(
        "strspn",
        constants$43.strspn$FUNC
    );
}


