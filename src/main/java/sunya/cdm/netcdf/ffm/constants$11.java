// Generated by jextract

package sunya.cdm.netcdf.ffm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$11 {

    static final FunctionDescriptor nc_get_vars$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_get_vars$MH = RuntimeHelper.downcallHandle(
        "nc_get_vars",
        constants$11.nc_get_vars$FUNC
    );
    static final FunctionDescriptor nc_put_varm$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_put_varm$MH = RuntimeHelper.downcallHandle(
        "nc_put_varm",
        constants$11.nc_put_varm$FUNC
    );
    static final FunctionDescriptor nc_get_varm$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_get_varm$MH = RuntimeHelper.downcallHandle(
        "nc_get_varm",
        constants$11.nc_get_varm$FUNC
    );
    static final FunctionDescriptor nc_def_var_quantize$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle nc_def_var_quantize$MH = RuntimeHelper.downcallHandle(
        "nc_def_var_quantize",
        constants$11.nc_def_var_quantize$FUNC
    );
    static final FunctionDescriptor nc_inq_var_quantize$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_inq_var_quantize$MH = RuntimeHelper.downcallHandle(
        "nc_inq_var_quantize",
        constants$11.nc_inq_var_quantize$FUNC
    );
    static final FunctionDescriptor nc_def_var_deflate$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle nc_def_var_deflate$MH = RuntimeHelper.downcallHandle(
        "nc_def_var_deflate",
        constants$11.nc_def_var_deflate$FUNC
    );
}


