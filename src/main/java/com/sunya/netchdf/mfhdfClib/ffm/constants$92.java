// Generated by jextract

package com.sunya.netchdf.mfhdfClib.ffm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$92 {

    static final FunctionDescriptor HCPgetcomptype$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_SHORT$LAYOUT,
        Constants$root.C_SHORT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle HCPgetcomptype$MH = RuntimeHelper.downcallHandle(
        "HCPgetcomptype",
        constants$92.HCPgetcomptype$FUNC
    );
    static final FunctionDescriptor HCPgetdatasize$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_SHORT$LAYOUT,
        Constants$root.C_SHORT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle HCPgetdatasize$MH = RuntimeHelper.downcallHandle(
        "HCPgetdatasize",
        constants$92.HCPgetdatasize$FUNC
    );
    static final FunctionDescriptor HCget_config_info$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle HCget_config_info$MH = RuntimeHelper.downcallHandle(
        "HCget_config_info",
        constants$92.HCget_config_info$FUNC
    );
    static final FunctionDescriptor HCPquery_encode_header$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle HCPquery_encode_header$MH = RuntimeHelper.downcallHandle(
        "HCPquery_encode_header",
        constants$92.HCPquery_encode_header$FUNC
    );
    static final FunctionDescriptor HCPencode_header$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle HCPencode_header$MH = RuntimeHelper.downcallHandle(
        "HCPencode_header",
        constants$92.HCPencode_header$FUNC
    );
    static final FunctionDescriptor HCPdecode_header$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle HCPdecode_header$MH = RuntimeHelper.downcallHandle(
        "HCPdecode_header",
        constants$92.HCPdecode_header$FUNC
    );
}


