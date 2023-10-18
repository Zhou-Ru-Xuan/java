package com.zhouruxuan.jvm.gc;

import org.junit.jupiter.api.Test;

import java.lang.ref.WeakReference;

public class ReferenceTypeTest {
    @Test
    public void testWeekReference() {
        // Strong Reference
        Gfg g = new Gfg();
        g.x();

        // Creating Weak Reference to Gfg-type object to which 'g'
        // is also pointing.
        WeakReference<Gfg> weakref = new WeakReference<Gfg>(g);

        //Now, Gfg-type object to which 'g' was pointing earlier
        //is available for garbage collection.
        //But, it will be garbage collected only when JVM needs memory.
        g = null;

        // You can retrieve back the object which
        // has been weakly referenced.
        // It successfully calls the method.
        g = weakref.get();

        g.x();

        System.gc();

        g.x();
    }
}

class Gfg {
    //code
    public void x() {
        System.out.println("GeeksforGeeks");
    }
}