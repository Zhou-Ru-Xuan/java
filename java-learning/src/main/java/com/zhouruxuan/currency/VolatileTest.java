package com.zhouruxuan.currency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class VolatileTest {
    private static boolean stopRequested = false;

    @Test
    public void testVisibility() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            long i = 0;
            while (!stopRequested) {
                i++;
            }
            System.out.println(i);//打印输出，用于对比加了volatile与不加volatile的打印速度
        }, "A");

        Thread thread2 = new Thread(() -> {
            stopRequested = true;
        }, "B");

        thread1.start();
        TimeUnit.SECONDS.sleep(1);    //为了演示死循环，特意sleep一秒
        thread2.start();
        thread1.join();
    }
}
