// Generated by jextract

package sunya.cdm.netcdf.ffm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$13 {

    static final FunctionDescriptor nc_inq_var_chunking$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_inq_var_chunking$MH = RuntimeHelper.downcallHandle(
        "nc_inq_var_chunking",
        constants$13.nc_inq_var_chunking$FUNC
    );
    static final FunctionDescriptor nc_def_var_fill$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_def_var_fill$MH = RuntimeHelper.downcallHandle(
        "nc_def_var_fill",
        constants$13.nc_def_var_fill$FUNC
    );
    static final FunctionDescriptor nc_inq_var_fill$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_inq_var_fill$MH = RuntimeHelper.downcallHandle(
        "nc_inq_var_fill",
        constants$13.nc_inq_var_fill$FUNC
    );
    static final FunctionDescriptor nc_def_var_endian$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle nc_def_var_endian$MH = RuntimeHelper.downcallHandle(
        "nc_def_var_endian",
        constants$13.nc_def_var_endian$FUNC
    );
    static final FunctionDescriptor nc_inq_var_endian$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_inq_var_endian$MH = RuntimeHelper.downcallHandle(
        "nc_inq_var_endian",
        constants$13.nc_inq_var_endian$FUNC
    );
    static final FunctionDescriptor nc_def_var_filter$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle nc_def_var_filter$MH = RuntimeHelper.downcallHandle(
        "nc_def_var_filter",
        constants$13.nc_def_var_filter$FUNC
    );
}


