package com.zhouruxuan.currency.someproblem;

import org.junit.jupiter.api.Test;

public class AddTest2 {
    private volatile long count = 0; //volatile修饰后，总会写到内存，总会从内存读

    @Test
    public void testConcurrencyAdd() throws InterruptedException {
        AddTest2 test = new AddTest2();
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
