// Generated by jextract

package com.sunya.netchdf.netcdfClib.ffm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$19 {

    static final FunctionDescriptor nc_inq_attlen$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_inq_attlen$MH = RuntimeHelper.downcallHandle(
        "nc_inq_attlen",
        constants$19.nc_inq_attlen$FUNC
    );
    static final FunctionDescriptor nc_inq_attname$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_inq_attname$MH = RuntimeHelper.downcallHandle(
        "nc_inq_attname",
        constants$19.nc_inq_attname$FUNC
    );
    static final FunctionDescriptor nc_copy_att$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle nc_copy_att$MH = RuntimeHelper.downcallHandle(
        "nc_copy_att",
        constants$19.nc_copy_att$FUNC
    );
    static final FunctionDescriptor nc_rename_att$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_rename_att$MH = RuntimeHelper.downcallHandle(
        "nc_rename_att",
        constants$19.nc_rename_att$FUNC
    );
    static final FunctionDescriptor nc_del_att$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_del_att$MH = RuntimeHelper.downcallHandle(
        "nc_del_att",
        constants$19.nc_del_att$FUNC
    );
    static final FunctionDescriptor nc_put_att_text$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_put_att_text$MH = RuntimeHelper.downcallHandle(
        "nc_put_att_text",
        constants$19.nc_put_att_text$FUNC
    );
}


