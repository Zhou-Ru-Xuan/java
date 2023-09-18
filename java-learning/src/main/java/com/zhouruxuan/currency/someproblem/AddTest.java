package com.zhouruxuan.currency.someproblem;

import org.junit.Test;

public class AddTest {
    private long count = 0;

    @Test
    public void testConcurrencyAdd() throws InterruptedException {
        AddTest test = new AddTest();
        // 创建两个线程，执行 add() 操作
        Thread th1 = new Thread(test::add10K);
        Thread th2 = new Thread(test::add10K);
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        System.out.println(test.count);
    }

    private void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
            count += 1;
        }
    }
}