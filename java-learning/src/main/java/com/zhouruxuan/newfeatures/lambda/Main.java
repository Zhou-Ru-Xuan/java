package com.zhouruxuan.newfeatures.lambda;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-30
 **/
public class Main {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("Anonymous Class Thread run()")).start();
    }
}
