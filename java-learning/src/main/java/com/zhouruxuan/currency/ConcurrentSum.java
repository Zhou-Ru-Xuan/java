package com.zhouruxuan.currency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentSum {
    private static final int THREAD_COUNT = 10;
    private static final long TARGET = 1000000000L;
    private static final AtomicLong sum = new AtomicLong(0);

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        long start = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.execute(new Counter());
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.yield();
        }
        long end = System.currentTimeMillis();
        System.out.println("Thread Count: " + THREAD_COUNT);
        System.out.println("Sum: " + sum.get());
        System.out.println("Time: " + (end - start) + "ms");
    }

    private static class Counter implements Runnable {
        @Override
        public void run() {
            for (long i = 0; i < TARGET / THREAD_COUNT; i++) {
                sum.incrementAndGet();
            }
        }
    }
}
