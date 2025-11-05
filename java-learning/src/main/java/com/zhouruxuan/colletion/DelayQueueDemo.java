package com.zhouruxuan.colletion;

import java.util.concurrent.*;

class DelayedTask implements Delayed, Runnable {
    private final String name;
    private final long startTime;
    
    public DelayedTask(String name, long delayMs) {
        this.name = name;
        this.startTime = System.currentTimeMillis() + delayMs;
    }
    
    @Override
    public void run() {
        System.out.printf("[%s] 执行延迟任务: %s (延迟: %dms)%n",
                         Thread.currentThread().getName(), name,
                         System.currentTimeMillis() - (startTime - 2000));
    }
    
    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }
    
    @Override
    public int compareTo(Delayed other) {
        return Long.compare(this.startTime, ((DelayedTask)other).startTime);
    }
}

public class DelayQueueDemo {
    public static void main(String[] args) {
        // 创建延迟队列
        BlockingQueue<Runnable> queue = new DelayQueue();
        
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            1, 1, 0, TimeUnit.SECONDS, queue);
        
        System.out.println("===== DelayQueue 测试 =====");
        System.out.println("开始时间: " + System.currentTimeMillis());
        
        // 提交延迟任务
        executor.execute(new DelayedTask("任务1 (2秒)", 2000));
        executor.execute(new DelayedTask("任务2 (1秒)", 1000));
        executor.execute(new DelayedTask("任务3 (3秒)", 3000));
        
        // 监控线程
        new Thread(() -> {
            while (!executor.isTerminated()) {
                System.out.println("队列大小: " + queue.size());
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
