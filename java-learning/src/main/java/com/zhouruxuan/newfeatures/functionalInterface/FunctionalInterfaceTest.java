package com.zhouruxuan.newfeatures.functionalInterface;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;
import java.util.function.Supplier;

public class FunctionalInterfaceTest {

    @Test
    public void testFunctionalInterface() {
        Foo foo = parameter -> parameter + " from lambda";
        Assert.assertEquals("Message  from lambda", add("Message ", foo));

        Function<String, String> fn = parameter -> parameter + " from lambda";
        Assert.assertEquals("Message  from lambda", add("Message ", fn));
    }

    @Test
    public void testSupplier() {
        Supplier<Integer> step1 = () -> genNum(1);
        Supplier<Integer> step2 = () -> genNum(2);
        Supplier<Integer> step3 = () -> genNum(3);

        Assert.assertEquals(3, step3.get().intValue());
    }

    private int genNum(int num) {
        System.out.println(num);
        return num;
    }

    public String add(String string, Foo foo) {
        return foo.method(string);
    }

    public String add(String string, Function<String, String> fn) {
        return fn.apply(string);
    }
}

@FunctionalInterface //这个注解没有实际含义，只是告诉大家这个接口是Functional Interface，不要在里面加第二个抽象方法
interface Foo {
    String method(String string);

    // String method2(String str); 上面的Foo foo = parameter -> parameter + " from lambda";会报错

    default String method3(String str) {
        return "String3";
    }
}