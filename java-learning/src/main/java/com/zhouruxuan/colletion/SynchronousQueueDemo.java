package com.zhouruxuan.colletion;

import java.util.concurrent.*;

public class SynchronousQueueDemo {
    public static void main(String[] args) {
        // 创建同步队列
        BlockingQueue<Runnable> queue = new SynchronousQueue<>();
        
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            0, 5, 30, TimeUnit.SECONDS,
            queue
        );
        
        System.out.println("===== SynchronousQueue 测试 =====");
        for (int i = 1; i <= 8; i++) {
            final int taskId = i;
            executor.execute(() -> {
                try {
                    System.out.printf("[%s] 任务-%d 开始 (队列大小: %d)%n",
                                     Thread.currentThread().getName(), taskId,
                                     queue.size());
                    Thread.sleep(1000);
                    System.out.printf("[%s] 任务-%d 完成 ✅%n",
                                     Thread.currentThread().getName(), taskId);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            System.out.println("已提交任务-" + taskId);
        }
        
        // 监控线程数变化
        new Thread(() -> {
            while (!executor.isTerminated()) {
                System.out.println("活跃线程数: " + executor.getActiveCount() + 
                                  "/总线程数: " + executor.getPoolSize());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
        
        executor.shutdown();
    }
}
