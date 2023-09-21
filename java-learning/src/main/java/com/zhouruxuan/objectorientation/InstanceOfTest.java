package com.zhouruxuan.objectorientation;

import org.junit.jupiter.api.Test;

public class InstanceOfTest {
    @Test
    public void testInstanceOf() {
        A a = new A();
        A b = new B();
        System.out.println(b instanceof A);
        System.out.println(b instanceof B);
        System.out.println(b instanceof C);
    }
}

class A {

}

class B extends A {

}

class C extends A{

}