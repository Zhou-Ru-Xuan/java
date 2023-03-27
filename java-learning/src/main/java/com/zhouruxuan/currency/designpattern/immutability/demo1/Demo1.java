package com.zhouruxuan.currency.designpattern.immutability.demo1;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-27
 **/
public class Demo1 {
    public static void main(String[] args) {
        Foo foo = new Foo();
        Bar bar = new Bar(foo);
        bar.setAge(9);
        System.out.println(foo.age == bar.foo.age);//true
    }

    static final class Foo {
        private int age = 0;
    }

    static final class Bar {
        final Foo foo;

        public Bar(Foo foo) {
            this.foo = foo;
        }

        void setAge(int a) {
            foo.age = a;
        }
    }
}

