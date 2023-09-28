package com.zhouruxuan.currency.concurrencyutil;

import java.util.concurrent.*;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // 创建一个核心线程数为2，最大线程数为3，队列容量为1的线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.AbortPolicy());

        // 提交5个任务
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            try {
                executor.execute(() -> {
                    System.out.println("Task " + taskId + " is running by " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(3000); // 模拟任务执行时间
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } catch (RejectedExecutionException e) {
                System.out.println("Task " + taskId + " is rejected.");
            }
        }

        // 关闭线程池
        executor.shutdown();
    }
}
