package com.zhouruxuan.jvm.classload.initializing;

public class StaticTest {
    public static void main(String[] args) {
        new StaticTest();
    }

    final int a = 1;
    public static int num = 1;
    public static int price;

    static {
        price = 10;
    }
}

