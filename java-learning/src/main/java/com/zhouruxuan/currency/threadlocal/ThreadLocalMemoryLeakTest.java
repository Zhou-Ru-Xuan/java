package com.zhouruxuan.currency.threadlocal;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal内存泄漏
 */
public class ThreadLocalMemoryLeakTest {

    static class LocalVar {
        //总共有5M
        private byte[] byteArray5M = new byte[1024 * 1024 * 5];
    }

  	//开启一个线程池，核心线程为6，最大线程为6的线程池
    final static ThreadPoolExecutor executorService = new ThreadPoolExecutor(6,
            6, 1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(100));

    static ThreadLocal<LocalVar> resources = new ThreadLocal<LocalVar>();

    public static void main(String[] args) throws InterruptedException {
      	
        for (int i = 0; i < 50; ++i) {
            executorService.execute(() -> {
                LocalVar localVar = new LocalVar();
                resources.set(localVar);
                System.out.println("thread run end：" + Thread.currentThread().getName() + ", value:" + resources.get());
                 resources.remove();
            });
            Thread.sleep(2000);
        }
        resources = null;
        System.out.println("主线程结束");
    }
}