package com.zhouruxuan.grammer;

import org.junit.Test;

public class IntegerTest {
    @Test
    public void testInteger(){
        String s = "19:00";
        int num = Integer.parseInt(s);
        System.out.println(num);
    }
}
