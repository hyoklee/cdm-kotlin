// Generated by jextract

package sunya.cdm.netcdf.ffm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$7 {

    static final FunctionDescriptor nc_def_vlen$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_def_vlen$MH = RuntimeHelper.downcallHandle(
        "nc_def_vlen",
        constants$7.nc_def_vlen$FUNC
    );
    static final FunctionDescriptor nc_inq_vlen$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_inq_vlen$MH = RuntimeHelper.downcallHandle(
        "nc_inq_vlen",
        constants$7.nc_inq_vlen$FUNC
    );
    static final FunctionDescriptor nc_free_vlen$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_free_vlen$MH = RuntimeHelper.downcallHandle(
        "nc_free_vlen",
        constants$7.nc_free_vlen$FUNC
    );
    static final FunctionDescriptor nc_free_vlens$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_free_vlens$MH = RuntimeHelper.downcallHandle(
        "nc_free_vlens",
        constants$7.nc_free_vlens$FUNC
    );
    static final FunctionDescriptor nc_put_vlen_element$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_put_vlen_element$MH = RuntimeHelper.downcallHandle(
        "nc_put_vlen_element",
        constants$7.nc_put_vlen_element$FUNC
    );
    static final FunctionDescriptor nc_get_vlen_element$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_get_vlen_element$MH = RuntimeHelper.downcallHandle(
        "nc_get_vlen_element",
        constants$7.nc_get_vlen_element$FUNC
    );
}


