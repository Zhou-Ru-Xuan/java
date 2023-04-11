package com.zhouruxuan.jvm.exception;

public class ExceptionTest {

    public static void main(String[] args) {
        try {
            mayArithmeticException(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mayArithmeticException(int num) {
        double result = 1 / num;
    }
}