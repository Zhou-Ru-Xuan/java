package com.zhouruxuan.currency.synchronized2;

public class SynchronizedAndInterrupt implements Runnable {

    boolean stop = false;

    public SynchronizedAndInterrupt() {
        new Thread(() -> foo(), "thread-A").start();
    }

    public synchronized void foo() {
        while (!stop) {
            System.out.println(Thread.currentThread() + "" + System.currentTimeMillis());
            // 让该循环持续一段时间，使上面的话打印次数少点
            long time = System.currentTimeMillis();
            while ((System.currentTimeMillis() - time < 1000)) {
            }
        }
    }

    @Override
    public void run() {
        while (!stop) {
            if (Thread.interrupted()) {
                stop = true;
                break;
            } else {
                foo();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedAndInterrupt obj = new SynchronizedAndInterrupt();
        Thread thread = new Thread(obj);
        thread.start();
        Thread.sleep(5000);
        System.out.println(thread.getName() + " Interrupting thread...");
        thread.interrupt();
        System.out.println(thread.getName() + " 线程" + thread + "是否中断：" + thread.isInterrupted());
    }
}
