package com.zhouruxuan.currency.thread;

import org.junit.Test;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-26
 **/
public class Test01 {
    @Test
    public void test1() {
        Thread userThread = new Thread(() -> {
            System.out.println("This is a user thread.");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("User thread finished.");
        });

        Thread daemonThread = new Thread(() -> {
            System.out.println("This is a daemon thread.");
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Daemon thread finished.");
        });
        //通过setDaemon手动设置
        daemonThread.setDaemon(true);

        userThread.start();
        daemonThread.start();

        System.out.println("Main thread finished.");
    }
}
