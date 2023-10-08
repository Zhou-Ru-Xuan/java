package com.zhouruxuan.currency.concurrencyutil;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    private static final int SEMAPHORE_COUNT = 1;
    private static final Semaphore semaphore = new Semaphore(SEMAPHORE_COUNT);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                // 获取信号量
                semaphore.acquire();
                // 执行临界区代码
                System.out.println("Thread 1 is running");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放信号量
                semaphore.release();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                // 获取信号量
                semaphore.acquire();
                // 执行临界区代码
                System.out.println("Thread 2 is running");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放信号量
                semaphore.release();
            }
        });

        t1.start();
        t2.start();
    }
}
