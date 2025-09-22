package com.zhouruxuan.currency.concurrencyutil;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class ThreadPoolExample {
    /**
     * 测试CallerRunsPolicy
     *
     * @throws InterruptedException
     */
    @Test
    public void CallerRunsPolicy_test() throws InterruptedException {
        // 1. 创建线程池（核心2线程，最大2线程，队列容量1）
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,  // corePoolSize
                2,  // maximumPoolSize
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1),  // 队列容量1
                new ThreadPoolExecutor.CallerRunsPolicy() // 关键策略
        );

        // 2. 提交6个任务（超过容量）
        System.out.println("===== 开始提交任务 =====");
        for (int i = 1; i <= 6; i++) {
            final int taskId = i;
            System.out.printf("主线程提交任务-%d%n", taskId);
            executor.execute(() -> {
                try {
                    String threadName = Thread.currentThread().getName();
                    System.out.printf("[%s] 任务-%d 开始执行...%n", threadName, taskId);
                    Thread.sleep(5000); // 模拟耗时操作
                    System.out.printf("[%s] 任务-%d 执行完成 ✅%n", threadName, taskId);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            Thread.sleep(100); // 稍微延迟，方便观察
        }

        // 3. 关闭线程池
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("===== 所有任务处理完成 =====");

    }

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
