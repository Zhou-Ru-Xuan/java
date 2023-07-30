package com.zhouruxuan.currency;

public class Main {
    public static void main(String[] args) {
        int sum = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000L; i++) {
            sum++;
        }
        long end = System.currentTimeMillis();

        System.out.println(sum);
        System.out.println(end - start);
    }
}
