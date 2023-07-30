package com.zhouruxuan.currency;

public class WaitNotifyExample {
    private static final Object lock = new Object();
    private static boolean condition = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                while (!condition) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Thread 1 is running");
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 2 is running");
                condition = true;
                lock.notify();
            }
        });

        t1.start();
        t2.start();
    }
}