package com.zhouruxuan.grammer;

import org.junit.Test;

public class DataTypeTest {
    @Test
    public void testByteShort() {
        byte a = 1; //byte a = (byte) 1
    }

    @Test
    public void dataTypeTest() {
        byte s1 = 127;
        // s1 =  s1 + 1; //exception
        s1 += 1;
        System.out.println(s1); // -128
    }
}
