package com.zhouruxuan.currency.threadlocal;

public class ThreadLocalTest {

    private static final ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
    private static final ThreadLocal<String> threadLocal2 = new ThreadLocal<>();


    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            ThreadLocal<Entity> threadLocal1 = new ThreadLocal<>();
            threadLocal1.set(new Entity("threadLocal1"));
            System.out.println(threadLocal1.get());
            ThreadLocal<Entity> threadLocal2 = new ThreadLocal<>();
            threadLocal2.set(new Entity("threadLocal2"));
            System.out.println(threadLocal2.get());
        });
        t.start();
    }
}

