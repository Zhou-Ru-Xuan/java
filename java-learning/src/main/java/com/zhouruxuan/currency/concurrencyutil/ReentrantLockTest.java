package com.zhouruxuan.currency.concurrencyutil;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    /**
     * lock()和lockInterruptibly()都是用于获取锁，但是它们在处理线程中断时的行为是不同的。
     * <p>
     * lock(): 当线程调用这个方法后，如果锁被其他线程持有，那么当前线程会被阻塞，直到锁被释放。在等待锁的过程中，如果线程被中断，那么中断状态会被设置，但是线程仍然会继续等待获取锁，直到它获取到锁或者锁不再被其他线程持有。
     * <p>
     * lockInterruptibly(): 当线程调用这个方法后，如果锁被其他线程持有，那么当前线程会被阻塞，直到锁被释放。但是，如果在等待锁的过程中线程被中断，那么它会立即抛出InterruptedException异常，并且中断状态会被清除。
     * <p>
     * 简单来说，lock()对中断不敏感，而lockInterruptibly()对中断敏感。如果你的线程需要对中断做出响应，那么应该使用lockInterruptibly()。
     */
    @Test
    public void testLockInterruptibly() {
        ReentrantLock lock = new ReentrantLock();
        Thread thread = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + " got lock");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        });
        thread.start();
        thread.interrupt();
    }

    boolean conditionMet = false;

    @Test
    public void testCondition() {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread thread = new Thread(() -> {
            lock.lock();
            try {
                while (!conditionMet) {
                    condition.await();
                }
                System.out.println("Condition met, doing work");
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            } finally {
                lock.unlock();
            }
        });
        thread.start();

        // 在主线程中改变条件并通知等待的线程
        lock.lock();
        try {
            Thread.sleep(2000);
            conditionMet = true;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

