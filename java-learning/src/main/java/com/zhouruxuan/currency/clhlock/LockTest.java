package com.zhouruxuan.currency.clhlock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;

public class LockTest {
    static int count = 0;
    
    public static void add10M(Lock lock) {
        try {
            lock.lock();
            for (int i = 0; i < 10000000; i++) ++count;
        } finally {
            lock.unlock();
        }
    }
    
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        final ClhSpinLock clh = new ClhSpinLock();
        final CyclicBarrier cb = new CyclicBarrier(10, () -> System.out.println(count));
        
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                add10M(clh);
                // 这段代码是非lock比较使用
               // for (int i = 0; i < 10000000; i++)
               //     count++;
                try {
                    cb.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}