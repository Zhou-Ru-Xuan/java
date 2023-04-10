package com.zhouruxuan.jvm.classload;

class StaticB {

    static {
        System.out.println("StaticB clinit start");
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
        System.out.println("开始争夺StaticA资源");
        try {
            Class.forName("com.zhouruxuan.jvm.classload.StaticA");
        } catch (ClassNotFoundException ignored) {
        }
        System.out.println("夺得StaticA资源");
        System.out.println("StaticB clinit over");
    }
}

