package com.zhouruxuan.currency.interrupt;

import org.junit.jupiter.api.Test;

public class InterruptTest {
    /**
     * 一个正在运行的线程，interrupt是不会中断的
     *
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        Thread thread = new Thread(new TestThread(), "My Thread");
        System.out.println("Starting thread...");
        thread.start();
        Thread.sleep(3000);
        System.out.println("Interrupting thread...");
        thread.interrupt();
        System.out.println("线程是否中断：" + thread.isInterrupted());
        Thread.sleep(3000);
        System.out.println("Stopping application...");
    }

    /**
     * 正确的做法
     *
     * @throws InterruptedException
     */
    @Test
    public void test2() throws InterruptedException {
        Thread thread = new Thread(new TestThread2(), "My Thread2");
        System.out.println("Starting thread...");
        thread.start();
        Thread.sleep(3000);
        System.out.println("Interrupting thread...");
        thread.interrupt();
        System.out.println("线程是否中断：" + thread.isInterrupted());
        Thread.sleep(3000);
        System.out.println("Stopping application...");
    }
}


class TestThread implements Runnable {
    boolean stop = false;

    @Override
    public void run() {
        while (!stop) {
            System.out.println("My Thread is running...");
            // 让该循环持续一段时间，使上面的话打印次数少点
            long time = System.currentTimeMillis();
            while ((System.currentTimeMillis() - time < 1000)) {
            }
        }
        System.out.println("My Thread exiting under request...");
    }
}

class TestThread2 implements Runnable {
    boolean stop = false;

    public void run() {
        while (!stop) {
            System.out.println("My Thread is running...");
            // 让该循环持续一段时间，使上面的话打印次数少点
            long time = System.currentTimeMillis();
            while ((System.currentTimeMillis() - time < 1000)) {
            }
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
        }
        System.out.println("My Thread exiting under request...");
    }
}