package com.zhouruxuan.objectorientation;

import org.junit.Test;

public class PolymorphismTest {
    @Test
    public void testPolymorphism() {
        SuperClass superClass = new SubClass();
        superClass.print();//1

        SuperClass superClass2 = new SuperClass();
        SubClass subClass = (SubClass) superClass2; //java.lang.ClassCastException
        subClass.print();
    }
}

class SuperClass {

    void print() {
        System.out.println(0);
    }
}

class SubClass extends SuperClass {
    void print() {
        System.out.println(1);
    }
}