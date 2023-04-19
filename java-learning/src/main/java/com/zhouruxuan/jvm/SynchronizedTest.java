package com.zhouruxuan.jvm;


public class SynchronizedTest {

    public static void lockTest() {
        Worker worker = new Worker();
        synchronized (worker) {
            worker.makeMoney();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        new Thread(() -> {
            for (int i = 0; i < 5000000; i++) {
                lockTest();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 5000000; i++) {
                lockTest();
            }
        }, "B").start();

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}