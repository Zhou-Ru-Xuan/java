package com.zhouruxuan.jvm.datatype;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Foo {

    boolean flag = false;

    public boolean getFlag() {
        return this.flag;
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Foo foo = new Foo();
        Field field = Foo.class.getDeclaredField("flag");
        Unsafe unsafe = getUnsafeByConstructor();
        long addr = unsafe.objectFieldOffset(field);

        for (byte b = 2; b < 4; b++) {
            System.out.println("Unsafe.putByte: " + b);
            unsafe.putByte(foo, addr, b);

            System.out.println("Unsafe.getByte: " + unsafe.getByte(foo, addr)); // 总是会打印出put的值
            System.out.println(
                    "Unsafe.getBoolean: " + unsafe.getBoolean(foo, addr)); // 打印出的值，像是ifeq, flag != 0即true

            // ifeq，flag != 0即true
            if (foo.flag) {
                System.out.println("foo.flag，");
            }

            //  if_cmpne 做整数比较，1 == flag，则为true
            if (true == foo.flag) {
                System.out.println("true == foo.flag，");
            }

            // ifeq，(flag) & 1 !=0 即true
            if (foo.getFlag()) {
                System.out.println("foo.getFlag()，");
            }
            // if_cmpne 做整数比较，,getFlag方法会对 boolean内容进行掩码操作，1 == (flag) & 1，则为true
            if (true == foo.getFlag()) {
                System.out.println("true == foo.getFlag()，");
            }

            System.out.println("-----------------------------------------------------------");
        }
    }

    private static Unsafe getUnsafeByConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<Unsafe> unsafeConstructor = Unsafe.class.getDeclaredConstructor();
        unsafeConstructor.setAccessible(true);
        return unsafeConstructor.newInstance();
    }
}

