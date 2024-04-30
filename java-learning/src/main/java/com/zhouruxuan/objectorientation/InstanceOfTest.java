package com.zhouruxuan.objectorientation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InstanceOfTest {

    @Test
    public void testInstanceOfString() {
        String s = "1";
        method1(s);
    }

    private void method1(Object o) {
        if (o instanceof String) {
            Assertions.assertEquals("1", o);
        }
    }

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

class C extends A {

}