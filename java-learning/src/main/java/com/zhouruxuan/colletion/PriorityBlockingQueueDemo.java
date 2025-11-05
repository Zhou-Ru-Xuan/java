package com.zhouruxuan.colletion;

import java.util.concurrent.*;

class PriorityTask implements Runnable, Comparable<PriorityTask> {
    private final int priority;
    private final String name;
    
    public PriorityTask(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("[%s] 执行任务: %s (优先级: %d)%n",
                         Thread.currentThread().getName(), name, priority);
    }
    
    @Override
    public int compareTo(PriorityTask other) {
        return Integer.compare(other.priority, this.priority); // 降序
    }
}

public class PriorityBlockingQueueDemo {
    public static void main(String[] args) {
        // 创建优先级队列
        BlockingQueue<Runnable> queue = new PriorityBlockingQueue<>();
        
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            1, 1, 0, TimeUnit.SECONDS, queue);
        
        System.out.println("===== PriorityBlockingQueue 测试 =====");
        // 提交不同优先级任务
        executor.execute(new PriorityTask("普通任务", 5));
        executor.execute(new PriorityTask("紧急任务", 1));
        executor.execute(new PriorityTask("重要任务", 3));
        executor.execute(new PriorityTask("低优先级", 8));
        
        executor.shutdown();
    }
}
