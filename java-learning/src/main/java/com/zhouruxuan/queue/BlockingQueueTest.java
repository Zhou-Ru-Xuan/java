package com.zhouruxuan.queue;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class BlockingQueueTest {


    @Test
    public void synchronousQueue_test() throws InterruptedException {
        // 1. 创建SynchronousQueue队列
        BlockingQueue<Integer> synchronousQueue = new SynchronousQueue<>();

        // 2. 启动一个线程，往队列中放3个元素
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " 入队列 1");
                synchronousQueue.put(1);
                System.out.println(Thread.currentThread().getName() + " 入队列 2");
                synchronousQueue.put(2);
                System.out.println(Thread.currentThread().getName() + " 入队列 3");
                synchronousQueue.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " 入队列 4");
                synchronousQueue.put(4);
                System.out.println(Thread.currentThread().getName() + " 入队列 5");
                synchronousQueue.put(5);
                System.out.println(Thread.currentThread().getName() + " 入队列 6");
                synchronousQueue.put(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 3. 等待1000毫秒
        Thread.sleep(1000L);

        // 4. 再启动一个线程，从队列中取出3个元素
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " 出队列 " + synchronousQueue.take());
                Thread.sleep(10000);
                System.out.println(Thread.currentThread().getName() + " 出队列 " + synchronousQueue.take());
                Thread.sleep(10000);
                System.out.println(Thread.currentThread().getName() + " 出队列 " + synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " 出队列 " + synchronousQueue.take());
                Thread.sleep(10000);
                System.out.println(Thread.currentThread().getName() + " 出队列 " + synchronousQueue.take());
                Thread.sleep(10000);
                System.out.println(Thread.currentThread().getName() + " 出队列 " + synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(10000);
    }

    @Test
    public void arrayBlockingQueue_test() {
        BlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(10);
    }

    @Test
    public void linkedBlockingDeque_test() {
        BlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>();

    }

    @Test
    public void delayQueue_test() {
        BlockingQueue<DelayedElement> delayQueue = new DelayQueue<>();
    }

    class DelayedElement implements Delayed {

        @Override
        public long getDelay(TimeUnit unit) {
            return 0;
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }
    }
}
