package com.zhouruxuan.jvm.method.methodhandle;

import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

public class Cobra {
    public static void race() {
        System.out.println("Cobra race()");
    }

    private void eat(){
        System.out.println("eat");
    }

    public static MethodHandles.Lookup lookup() {
        return MethodHandles.lookup();
    }

    @Test
    public void test1() throws Throwable {
        MethodHandles.Lookup lookup = Cobra.lookup();
        Method method = Cobra.class.getDeclaredMethod("race");
        MethodHandle methodHandle = lookup.unreflect(method);
        methodHandle.invoke();
    }

    @Test
    public void test2() throws Throwable {
        MethodHandles.Lookup lookup = Cobra.lookup();
        MethodType methodType = MethodType.methodType(void.class);
        MethodHandle methodHandle = lookup.findStatic(Cobra.class, "race", methodType);
        methodHandle.invoke();
    }

    @Test
    public void test3() throws Throwable {
        MethodHandles.Lookup lookup = Cobra.lookup();
        MethodType methodType = MethodType.methodType(void.class);
        MethodHandle methodHandle = lookup.findStatic(Cobra.class, "race", methodType);
        methodHandle.invoke();
    }
}