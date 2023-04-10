package com.zhouruxuan.jvm.method.binding;

public class Child extends Parent<String> {

    public void sayHello(String value) {
        System.out.println("This is Child class, value is " + value);
    }

    public static void main(String[] args) {
        Parent<String> object = new Child();
        object.sayHello("Java");
    }
}