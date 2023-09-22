package com.zhouruxuan.currency;

import org.junit.jupiter.api.Test;

public class SynchronizedTest {
    static int count = 0;

    /**
     * 测试synchronized修饰实例
     */
    @Test
    public void testInstance() {
        SynchronizedTest obj = new SynchronizedTest();
        Thread t1 = new Thread(obj::add, "A");
        Thread t2 = new Thread(obj::add, "B");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
            System.out.println("main线程输出结果为==>" + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInstance2() {
        SynchronizedTest obj = new SynchronizedTest();
        SynchronizedTest obj2 = new SynchronizedTest();
        Thread t1 = new Thread(obj::add, "A");
        Thread t2 = new Thread(obj2::add, "B");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
            System.out.println("main线程输出结果为==>" + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void add() {
        for (int i = 0; i < 100000; i++) {
            count++;
        }
    }

    @Test
    public void testStaticMethod() {

    }

    public synchronized static void staticAdd() {
        for (int i = 0; i < 100000; i++) {
            count++;
        }
    }
}
