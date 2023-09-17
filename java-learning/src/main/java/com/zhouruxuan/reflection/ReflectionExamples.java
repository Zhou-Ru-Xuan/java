package com.zhouruxuan.reflection;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.lang.reflect.*;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-09-17
 **/
public class ReflectionExamples {

    public ReflectionExamples() {
    }

    @Test
    public void showAllMethods() {
        try {
            Class c = Class.forName(this.getClass().getName());
            Method m[] = c.getDeclaredMethods();
            for (Method method : m) System.out.println(method.toString());
        } catch (Throwable e) {
            System.err.println(e);
        }
    }

    @Test
    public void useInstanceOf() {
        try {
            Class cls = Class.forName(A.class.getName());
            boolean b1 = cls.isInstance(37);
            System.out.println(b1);
            boolean b2 = cls.isInstance(new A());
            System.out.println(b2);
        } catch (Throwable e) {
            System.err.println(e);
        }
    }

    private int f1(Object p, int x) throws NullPointerException {
        if (p == null)
            throw new NullPointerException();
        return x;
    }

    @Test
    public void findOutAboutMethod() {
        try {
            Class cls = Class.forName(this.getClass().getName());
            Method[] methlist = cls.getDeclaredMethods();
            for (Method m : methlist) {
                System.out.println("name" + m.getName());
                System.out.println("decl class = " + m.getDeclaringClass());
                Class[] pvec = m.getParameterTypes();
                for (int j = 0; j < pvec.length; j++)
                    System.out.println(" param #" + j + " " + pvec[j]);
                Class[] evec = m.getExceptionTypes();
                for (int j = 0; j < evec.length; j++)
                    System.out.println("exc #" + j + " " + evec[j]);
                System.out.println("return type = " + m.getReturnType());
                System.out.println("-----");
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }

    @Test
    public void obtainInformationAboutConstructor() {
        A a = new A();
        a.obtainInformationAboutConstructor();
    }

}

@Getter
@Setter
class A {
    private Integer id;
    private String key;

    public A() {

    }

    public A(Integer id, String key) {
        this.id = id;
        this.key = key;
    }

    public void obtainInformationAboutConstructor() {
        try {
            Class cls = Class.forName(this.getClass().getName());
            Constructor[] ctorlist = cls.getDeclaredConstructors();
            for (Constructor ct : ctorlist) {
                System.out.println("name = " + ct.getName());
                System.out.println("decl class = " + ct.getDeclaringClass());
                Class[] pvec = ct.getParameterTypes();
                for (int j = 0; j < pvec.length; j++)
                    System.out.println("param #" + j + " " + pvec[j]);
                Class[] evec = ct.getExceptionTypes();
                for (int j = 0; j < evec.length; j++)
                    System.out.println("exc #" + j + " " + evec[j]);
                System.out.println("-----");
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
