// Generated by jextract

package sunya.cdm.netcdf.ffm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$20 {

    static final FunctionDescriptor nc_get_att_text$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_get_att_text$MH = RuntimeHelper.downcallHandle(
        "nc_get_att_text",
        constants$20.nc_get_att_text$FUNC
    );
    static final FunctionDescriptor nc_put_att_string$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_put_att_string$MH = RuntimeHelper.downcallHandle(
        "nc_put_att_string",
        constants$20.nc_put_att_string$FUNC
    );
    static final FunctionDescriptor nc_get_att_string$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_get_att_string$MH = RuntimeHelper.downcallHandle(
        "nc_get_att_string",
        constants$20.nc_get_att_string$FUNC
    );
    static final FunctionDescriptor nc_put_att_uchar$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_put_att_uchar$MH = RuntimeHelper.downcallHandle(
        "nc_put_att_uchar",
        constants$20.nc_put_att_uchar$FUNC
    );
    static final FunctionDescriptor nc_get_att_uchar$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_get_att_uchar$MH = RuntimeHelper.downcallHandle(
        "nc_get_att_uchar",
        constants$20.nc_get_att_uchar$FUNC
    );
    static final FunctionDescriptor nc_put_att_schar$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_put_att_schar$MH = RuntimeHelper.downcallHandle(
        "nc_put_att_schar",
        constants$20.nc_put_att_schar$FUNC
    );
}


