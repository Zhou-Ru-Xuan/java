package com.zhouruxuan.jvm.gc;

import java.util.concurrent.atomic.AtomicInteger;

public class SavePointTest {
    public static AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) throws Throwable {
        Runnable runnable = () -> {
            for (int i = 0; i < 1000000000; i++) {
                num.getAndAdd(1);
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();

        System.out.println("before sleep");
        Thread.sleep(1000);
        System.out.println("after sleep");

        System.out.println(num);
    }

}
