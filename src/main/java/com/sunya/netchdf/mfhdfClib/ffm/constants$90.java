// Generated by jextract

package com.sunya.netchdf.mfhdfClib.ffm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
class constants$90 {

    static final FunctionDescriptor HLcreate$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_SHORT$LAYOUT,
        Constants$root.C_SHORT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle HLcreate$MH = RuntimeHelper.downcallHandle(
        "HLcreate",
        constants$90.HLcreate$FUNC
    );
    static final FunctionDescriptor HLconvert$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle HLconvert$MH = RuntimeHelper.downcallHandle(
        "HLconvert",
        constants$90.HLconvert$FUNC
    );
    static final FunctionDescriptor HDinqblockinfo$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle HDinqblockinfo$MH = RuntimeHelper.downcallHandle(
        "HDinqblockinfo",
        constants$90.HDinqblockinfo$FUNC
    );
    static final FunctionDescriptor HLsetblockinfo$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle HLsetblockinfo$MH = RuntimeHelper.downcallHandle(
        "HLsetblockinfo",
        constants$90.HLsetblockinfo$FUNC
    );
    static final FunctionDescriptor HLgetblockinfo$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle HLgetblockinfo$MH = RuntimeHelper.downcallHandle(
        "HLgetblockinfo",
        constants$90.HLgetblockinfo$FUNC
    );
    static final FunctionDescriptor HLgetdatainfo$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle HLgetdatainfo$MH = RuntimeHelper.downcallHandle(
        "HLgetdatainfo",
        constants$90.HLgetdatainfo$FUNC
    );
}


