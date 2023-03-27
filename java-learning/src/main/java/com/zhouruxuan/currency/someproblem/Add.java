package com.zhouruxuan.currency.someproblem;

import java.util.concurrent.atomic.AtomicLong;

public class Add {
    private long count = 0;
    private final AtomicLong vCount = new AtomicLong();

    public static void main(String[] args) throws InterruptedException {
        Add test = new Add();
        // 创建两个线程，执行 add() 操作
        Thread th1 = new Thread(test::add);
        Thread th2 = new Thread(test::add);
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        System.out.println(test.count);
        System.out.println(test.vCount);
    }

    private void add() {
        int idx = 0;
        while (idx++ < 100000) {
            count += 1;
            vCount.addAndGet(1);
        }
    }

}