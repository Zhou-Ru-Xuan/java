package com.zhouruxuan.currency.concurrencyutil;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class ThreadPoolExample {
    /**
     * execute 执行出现异常，但不会中断主流程
     */
    @Test
    public void testExecute() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.AbortPolicy());
        executor.execute(() -> {
            int a = 1 / 0;
        });
        System.out.println("testExecute done");
    }

    /**
     * submit 可以返回执行结果
     */
    @Test
    public void testSubmit() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.AbortPolicy());
        Future<?> future = executor.submit(new Runnable() {
            @Override
            public void run() {
                // 执行任务，可能会抛出异常
                int a = 1 / 0;
            }
        });
        try {
            future.get(); // 获取任务结果，如果任务抛出异常，这里会抛出ExecutionException
        } catch (InterruptedException e) {
            // 处理InterruptedException异常
        } catch (ExecutionException e) {
            Throwable cause = e.getCause(); // 获取任务抛出的异常
            // 处理任务抛出的异常
            System.out.println(cause.getMessage());
        }
    }

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
