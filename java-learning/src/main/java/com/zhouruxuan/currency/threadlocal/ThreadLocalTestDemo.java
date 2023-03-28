package com.zhouruxuan.currency.threadlocal;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTestDemo {

    private static final ThreadLocal<A> threadLocal = new ThreadLocal<>();


    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>());

        for (int i = 0; i < 5; ++i) {
            threadPoolExecutor.execute(() -> {
                System.out.println("创建对象：");
                A a = new A();
                threadLocal.set(a);
                a = null; //将对象设置为 null，表示此对象不在使用了
                 threadLocal.remove();
            });
        }
    }

    static class A {
        // 100M
        private byte[] bytes = new byte[100 * 1024 * 1024];
    }
}