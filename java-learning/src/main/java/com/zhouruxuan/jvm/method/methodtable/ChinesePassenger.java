package com.zhouruxuan.jvm.method.methodtable;

public class ChinesePassenger extends Passenger {

    @Override
    void say() {
        System.out.println("说你好");
    }

    void eat() {
        System.out.println("吃包子");
    }

    public static void main(String[] args) {
        Passenger passenger = new ChinesePassenger();
        passenger.say();
    }
}