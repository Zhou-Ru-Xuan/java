package com.zhouruxuan.currency.designpattern.immutability.demo1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-27
 **/
public class Demo2 {
    public static void main(String[] args) {
        Foo foo = new Foo();
        Bar bar = new Bar(foo);
        bar.setAge(9);
    }

    //Foo线程安全
    @Getter
    @Setter
    static final class Foo {
        final int age = 0;
    }

    //Bar线程不安全
    @AllArgsConstructor
    @Setter
    @Getter
    static class Bar {
        private Foo foo;

        void setAge(int a) {
            //foo.setAge(a);  //final属性无法生成set方法
        }
    }
}
