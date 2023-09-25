package com.zhouruxuan.currency.concurrencyutil;

import java.util.concurrent.CyclicBarrier;

/**
 * 比如我们组织一次团队建设活动，活动分为三个阶段：集合、游戏、返回。每个阶段都需要所有人都准备好才能进行下一阶段。
 */
public class CyclicBarrierExample {

    public static void main(String[] args) {
        int num = 5; // 团队人数
        CyclicBarrier barrier = new CyclicBarrier(num, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有人都准备好了，可以进行下一阶段...");
            }
        });

        for (int i = 0; i < num; i++) {
            new Thread(new Worker(i, barrier)).start();
        }
    }

    static class Worker implements Runnable {
        private final int id;
        private final CyclicBarrier barrier;

        Worker(int id, CyclicBarrier barrier) {
            this.id = id;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("员工" + id + "正在前往集合地点...");
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println("员工" + id + "已到达集合地点，等待其他人...");
                barrier.await();

                System.out.println("员工" + id + "开始参加游戏...");
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println("员工" + id + "游戏结束，等待其他人...");
                barrier.await();

                System.out.println("员工" + id + "开始返回...");
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println("员工" + id + "已返回，等待其他人...");
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
