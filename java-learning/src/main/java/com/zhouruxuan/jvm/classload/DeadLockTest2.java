package com.zhouruxuan.jvm.classload;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-09
 **/
public class DeadLockTest2 {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Class.forName("com.zhouruxuan.jvm.classload.StaticA");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " over");
        }, "Thread-A").start();

        new Thread(() -> {
            try {
                Class.forName("com.zhouruxuan.jvm.classload.StaticB");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " over");
        }, "Thread-B").start();
    }
}
