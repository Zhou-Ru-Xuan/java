package com.zhouruxuan.currency.jmm;

import org.junit.Test;

public class HappensBeforeDemo {
    public int x = 0;
    public volatile boolean v = false;
    public final Object lock = new Object();

    @Test
    public void programOrderRule() {
        x = 1;  // 写操作
        System.out.println(x);  // 读操作
    }

    @Test
    public void lockRule() {
        new Thread(() -> {
            synchronized (lock) {
                x = 2;
            }
        }).start();
        //synchronized执行完自动释放锁

        new Thread(() -> {
            synchronized (lock) {
                System.out.println(x); //2
            }
        }).start();
    }

    @Test
    public void volatileRule() {
        new Thread(() -> {
            x = 3;  // 写操作
            v = true;
        }).start();

        new Thread(() -> {
            if (v) {  // 读操作
                System.out.println(x); //3
            }
        }).start();
    }

    @Test
    public void transmissionRule() {
        // 线程A
        new Thread(() -> {
            x = 4;
        }).start();

        // 线程B
        new Thread(() -> {
            if (x == 4) {
                x = 5;
            }
        }).start();

        // 线程C
        new Thread(() -> {
            if (x == 5) {
                System.out.println(x); //5
            }
        }).start();
    }

    @Test
    public void threadStartRule() {
        Thread thread = new Thread(() -> {
            x = 6;
            System.out.println(x);
        });
        thread.start();
    }

    @Test
    public void threadInterruptRule() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                // do something
            }
            System.out.println("线程被中断");
        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @Test
    public void threadTerminationRule() throws InterruptedException {
        Thread thread = new Thread(() -> {
            x = 7;
            System.out.println(x);
        });
        thread.start();
        thread.join();
        System.out.println("线程已终止");
    }

    @Test
    public void objectFinalizationRule() {
        DeadObject demo = new DeadObject();
        demo = null;
        System.gc();  // 提示JVM进行垃圾回收
    }

    class DeadObject{
        @Override
        protected void finalize() throws Throwable {
            System.out.println("对象被回收");
        }
    }
}
