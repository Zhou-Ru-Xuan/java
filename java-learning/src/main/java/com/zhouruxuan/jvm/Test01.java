package com.zhouruxuan.jvm;

import org.junit.Test;

import static java.lang.Float.NaN;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-05
 **/
public class Test01 {
    void invoke(Object obj, Object... args) {
        System.out.println("invoke1");
    }

    void invoke(String s, Object obj, Object... args) {
        System.out.println("invoke2");
    }

    @Test
    public void test01() {
        invoke(null, 1);    // 调用第二个invoke方法
        invoke(null, 1, 2); // 调用第二个invoke方法
        invoke(null, new Object[]{1}); // 只有手动绕开可变长参数的语法糖才能调用第一个invoke方法
    }

    @Test
    public void test02(){
        System.out.println(NaN);
        try {
            throw new RuntimeException();
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test03(){
        char c1 = 'D';
        char c2 = 5;
        char c3 = (char) (c1 + c2);
    }
}
