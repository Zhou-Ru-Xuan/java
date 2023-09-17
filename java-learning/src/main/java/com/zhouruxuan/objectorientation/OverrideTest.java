package com.zhouruxuan.objectorientation;

import org.junit.Test;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-09-17
 **/
public class OverrideTest {
    @Test
    public void testOverride(){
        Far f = new Far();
        f.c(); //F c()

        Far f1 = new Son();
        f1.c();//F c()

        Son s = new Son();
        s.c();//S c()

        Far.c();
        Son.c();
    }
}

class Far {
    static void c(){
        System.out.println("F c()");
    }
}


class Son extends Far {
    static void c(){
        System.out.println("S c()");
    }
}