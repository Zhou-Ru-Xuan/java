package com.zhouruxuan.newfeatures.lambda;

public class Hello {
    Runnable r1 = () -> System.out.println(this);

    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            System.out.println(this);
        }
    };

    public static void main(String[] args) {
        new Hello().r1.run();
        new Hello().r2.run();

    }

    public String toString() {
        return "Hello Hoolee";
    }
}