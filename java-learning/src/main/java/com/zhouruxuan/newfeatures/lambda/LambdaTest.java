package com.zhouruxuan.newfeatures.lambda;

import org.junit.Test;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-30
 **/
public class LambdaTest {
    @Test
    public void test01() {
        new Thread(() -> System.out.println("Anonymous Class Thread run()")).start();
    }

    @Test
    public void test02() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous Class Thread run()");
            }
        }).start();
    }
}
