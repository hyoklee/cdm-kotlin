// Generated by jextract

package com.sunya.netchdf.mfhdfClib.ffm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$82 {

    static final FunctionDescriptor Htell$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle Htell$MH = RuntimeHelper.downcallHandle(
        "Htell",
        constants$82.Htell$FUNC
    );
    static final FunctionDescriptor Hread$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle Hread$MH = RuntimeHelper.downcallHandle(
        "Hread",
        constants$82.Hread$FUNC
    );
    static final FunctionDescriptor Hwrite$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle Hwrite$MH = RuntimeHelper.downcallHandle(
        "Hwrite",
        constants$82.Hwrite$FUNC
    );
    static final FunctionDescriptor Htrunc$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle Htrunc$MH = RuntimeHelper.downcallHandle(
        "Htrunc",
        constants$82.Htrunc$FUNC
    );
    static final FunctionDescriptor Hendaccess$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle Hendaccess$MH = RuntimeHelper.downcallHandle(
        "Hendaccess",
        constants$82.Hendaccess$FUNC
    );
    static final FunctionDescriptor HDgetc$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle HDgetc$MH = RuntimeHelper.downcallHandle(
        "HDgetc",
        constants$82.HDgetc$FUNC
    );
}


