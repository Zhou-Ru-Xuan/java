package com.zhouruxuan.jvm.gc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CanReliveObj {

    public static CanReliveObj obj;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize");
        obj = this;
    }

    @Test
    public void test() throws InterruptedException {
        obj = new CanReliveObj();
        System.out.println("第一次gc");
        obj = null;
        System.gc();
        Thread.sleep(1000); // Java不保证finalize()方法会被及时调用，因此要等一会
        Assertions.assertNotNull(obj);
        System.out.println("第二次gc");
        obj = null;
        System.gc();
        Assertions.assertNull(obj);
    }
}
