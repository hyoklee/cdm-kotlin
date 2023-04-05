// Generated by jextract

package com.sunya.netchdf.hdf5Clib.ffm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
public class H5A_info_t {

    static final  GroupLayout $struct$LAYOUT = MemoryLayout.structLayout(
        Constants$root.C_BOOL$LAYOUT.withName("corder_valid"),
        MemoryLayout.paddingLayout(24),
        Constants$root.C_INT$LAYOUT.withName("corder"),
        Constants$root.C_INT$LAYOUT.withName("cset"),
        MemoryLayout.paddingLayout(32),
        Constants$root.C_LONG_LONG$LAYOUT.withName("data_size")
    );
    public static MemoryLayout $LAYOUT() {
        return H5A_info_t.$struct$LAYOUT;
    }
    static final VarHandle corder_valid$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("corder_valid"));
    public static VarHandle corder_valid$VH() {
        return H5A_info_t.corder_valid$VH;
    }
    public static boolean corder_valid$get(MemorySegment seg) {
        return (boolean)H5A_info_t.corder_valid$VH.get(seg);
    }
    public static void corder_valid$set( MemorySegment seg, boolean x) {
        H5A_info_t.corder_valid$VH.set(seg, x);
    }
    public static boolean corder_valid$get(MemorySegment seg, long index) {
        return (boolean)H5A_info_t.corder_valid$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void corder_valid$set(MemorySegment seg, long index, boolean x) {
        H5A_info_t.corder_valid$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle corder$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("corder"));
    public static VarHandle corder$VH() {
        return H5A_info_t.corder$VH;
    }
    public static int corder$get(MemorySegment seg) {
        return (int)H5A_info_t.corder$VH.get(seg);
    }
    public static void corder$set( MemorySegment seg, int x) {
        H5A_info_t.corder$VH.set(seg, x);
    }
    public static int corder$get(MemorySegment seg, long index) {
        return (int)H5A_info_t.corder$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void corder$set(MemorySegment seg, long index, int x) {
        H5A_info_t.corder$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle cset$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("cset"));
    public static VarHandle cset$VH() {
        return H5A_info_t.cset$VH;
    }
    public static int cset$get(MemorySegment seg) {
        return (int)H5A_info_t.cset$VH.get(seg);
    }
    public static void cset$set( MemorySegment seg, int x) {
        H5A_info_t.cset$VH.set(seg, x);
    }
    public static int cset$get(MemorySegment seg, long index) {
        return (int)H5A_info_t.cset$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void cset$set(MemorySegment seg, long index, int x) {
        H5A_info_t.cset$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle data_size$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("data_size"));
    public static VarHandle data_size$VH() {
        return H5A_info_t.data_size$VH;
    }
    public static long data_size$get(MemorySegment seg) {
        return (long)H5A_info_t.data_size$VH.get(seg);
    }
    public static void data_size$set( MemorySegment seg, long x) {
        H5A_info_t.data_size$VH.set(seg, x);
    }
    public static long data_size$get(MemorySegment seg, long index) {
        return (long)H5A_info_t.data_size$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void data_size$set(MemorySegment seg, long index, long x) {
        H5A_info_t.data_size$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(int len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemoryAddress addr, MemorySession session) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, session); }
}


