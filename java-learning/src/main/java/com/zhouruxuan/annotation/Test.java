package com.zhouruxuan.annotation;

import java.lang.reflect.Method;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-24
 **/
public class Test {
    @Hello("hello")
    public static void main(String[] args) throws NoSuchMethodException {
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        Class cls = Test.class;
        Method method = cls.getMethod("main", String[].class);
        Hello hello = method.getAnnotation(Hello.class);
        System.out.println(hello.value());
    }
}
