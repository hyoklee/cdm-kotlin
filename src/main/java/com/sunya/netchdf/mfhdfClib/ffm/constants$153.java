// Generated by jextract

package com.sunya.netchdf.mfhdfClib.ffm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$153 {

    static final FunctionDescriptor sd_ncabort$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle sd_ncabort$MH = RuntimeHelper.downcallHandle(
        "sd_ncabort",
        constants$153.sd_ncabort$FUNC
    );
    static final FunctionDescriptor sd_ncnobuf$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle sd_ncnobuf$MH = RuntimeHelper.downcallHandle(
        "sd_ncnobuf",
        constants$153.sd_ncnobuf$FUNC
    );
    static final FunctionDescriptor sd_ncdimdef$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle sd_ncdimdef$MH = RuntimeHelper.downcallHandle(
        "sd_ncdimdef",
        constants$153.sd_ncdimdef$FUNC
    );
    static final FunctionDescriptor sd_ncdimid$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle sd_ncdimid$MH = RuntimeHelper.downcallHandle(
        "sd_ncdimid",
        constants$153.sd_ncdimid$FUNC
    );
    static final FunctionDescriptor sd_ncdiminq$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle sd_ncdiminq$MH = RuntimeHelper.downcallHandle(
        "sd_ncdiminq",
        constants$153.sd_ncdiminq$FUNC
    );
    static final FunctionDescriptor sd_ncdimrename$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle sd_ncdimrename$MH = RuntimeHelper.downcallHandle(
        "sd_ncdimrename",
        constants$153.sd_ncdimrename$FUNC
    );
}


