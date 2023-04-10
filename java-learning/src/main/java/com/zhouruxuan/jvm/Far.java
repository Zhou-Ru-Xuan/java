package com.zhouruxuan.jvm;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-06
 **/
public class Far {

    public static void main(String[] args) {
        Son son = new Son();
        son.b(null, 1);
        son.b(null, 1, 2);
        son.b(null, new Object[]{1});

        Far f = new Far();
        f.c();

        Far f1 = new Son();
        f1.c();

        Son s = new Son();
        s.c();
    }

    private void a() {
        System.out.println("F a()");
    }

    void b(Object obj, Object... args) {
        System.out.println("F b()");
    }

    static void c(){
        System.out.println("F c()");
    }
}


class Son extends Far {
    private void a() {

    }

    public void b(String s, Object obj, Object... args) {
        System.out.println("S b()");
    }

    static void c(){
        System.out.println("S c()");
    }
}