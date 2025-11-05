package com.zhouruxuan.colletion;

import java.util.concurrent.*;

public class LinkedBlockingQueueDemo {
    public static void main(String[] args) {
        // 创建无界队列（默认Integer.MAX_VALUE）
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(); 
        
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2, 4, 30, TimeUnit.SECONDS,
            queue
        );
        
        System.out.println("===== LinkedBlockingQueue 测试 =====");
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            executor.execute(() -> {
                try {
                    System.out.printf("[%s] 任务-%d 开始 (队列大小: %d)%n",
                                     Thread.currentThread().getName(), taskId,
                                     queue.size());
                    Thread.sleep(500);
                    System.out.printf("[%s] 任务-%d 完成 ✅%n",
                                     Thread.currentThread().getName(), taskId);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            System.out.println("已提交任务-" + taskId);
        }
        
        // 监控队列增长
        new Thread(() -> {
            while (!executor.isTerminated()) {
                System.out.println("当前队列大小: " + queue.size());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
        
        executor.shutdown();
    }
}
