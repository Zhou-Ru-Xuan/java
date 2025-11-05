package com.zhouruxuan.colletion;

import java.util.concurrent.*;

public class ArrayBlockingQueueDemo {
    public static void main(String[] args) {
        // 创建线程池：核心2线程，最大4线程，使用容量为3的ArrayBlockingQueue
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2, 4, 30, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(3), // 有界队列
            new ThreadPoolExecutor.CallerRunsPolicy()
        );
        
        System.out.println("===== ArrayBlockingQueue 测试 =====");
        for (int i = 1; i <= 8; i++) {
            final int taskId = i;
            try {
                executor.execute(() -> {
                    try {
                        System.out.printf("[%s] 任务-%d 开始 (队列大小: %d)%n",
                                         Thread.currentThread().getName(), taskId,
                                         executor.getQueue().size());
                        Thread.sleep(10000);
                        System.out.printf("[%s] 任务-%d 完成 ✅%n",
                                         Thread.currentThread().getName(), taskId);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
                System.out.println("已提交任务-" + taskId);
                Thread.sleep(200); // 控制提交速度
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        executor.shutdown();
    }
}
