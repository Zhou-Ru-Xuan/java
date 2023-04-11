package com.zhouruxuan.jvm.method.methodinlining;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-10
 **/
public class MethodInlineTest {
    public static boolean flag = true;
    public static int value0 = 0;
    public static int value1 = 1;

    public static int foo(int value) {
        int result = bar(flag);
        if (result != 0) {
            return result;
        } else {
            return value;
        }
    }

    public static int bar(boolean flag) {
        return flag ? value0 : value1;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 20000; i++) {
            foo(i);
        }
    }
}
