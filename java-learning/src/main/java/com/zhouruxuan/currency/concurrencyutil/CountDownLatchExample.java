package com.zhouruxuan.currency.concurrencyutil;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    public static void main(String[] args) {
        int audienceNum = 5; // 观众人数
        CountDownLatch latch = new CountDownLatch(audienceNum);

        for (int i = 0; i < audienceNum; i++) {
            new Thread(new Audience(i, latch)).start();
        }

        try {
            System.out.println("等待所有观众到齐...");
            latch.await();
            System.out.println("所有观众已到齐，音乐会开始...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Audience implements Runnable {
        private final int id;
        private final CountDownLatch latch;

        Audience(int id, CountDownLatch latch) {
            this.id = id;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                System.out.println("观众" + id + "正在前往音乐会...");
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println("观众" + id + "已到达音乐会，等待开始...");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
