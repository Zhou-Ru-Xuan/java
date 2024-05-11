package com.zhouruxuan.objectorientation;

import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InstanceOfTest {

    @Test
    public void testInstanceOfString() {
        String s = "1";
        method1(s);
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        method1(list);
    }

    private void method1(Object o) {
        if (o instanceof String) {
            Assertions.assertEquals("1", o);
        } else if (o instanceof Collection) {
            Assertions.assertEquals(1, ((Collection) o).size());
            Assertions.assertTrue(CollectionUtils.isNotEmpty((Collection) o));
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