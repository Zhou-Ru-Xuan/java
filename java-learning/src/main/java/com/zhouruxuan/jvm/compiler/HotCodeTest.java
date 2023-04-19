package com.zhouruxuan.jvm.compiler;

public class HotCodeTest {

    public static void main(String[] args) {
        nlp();
    }

    public static void nlp() {
        int sum = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 20000000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("cost:" + (end - start) + " ms");
    }

}

