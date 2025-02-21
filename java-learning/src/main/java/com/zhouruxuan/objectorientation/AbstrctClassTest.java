package com.zhouruxuan.objectorientation;

import lombok.Builder;
import org.junit.Test;

public class AbstrctClassTest {
    @Test
    public void test() {
        SubAbstractClass subAbstractClass = new SubAbstractClass();
        subAbstractClass.method();
        Boolean a = null;
        Boolean b = false;
        System.out.println(null instanceof Boolean);
        System.out.println(a == b);
    }

    @Test
    public void instantiateAbstractClass() {
        // 不能实例化
//        AbstractClass abstractClass = new AbstractClass();
    }
}


abstract class AbstractClass {
    private int a;

    public abstract void method();

    public AbstractClass(int a) {
        this.a = a;
    }

    public AbstractClass() {

    }
}

@Builder
class SubAbstractClass extends AbstractClass {
    @Override
    public void method() {
        // implementation of the abstract method
    }
}