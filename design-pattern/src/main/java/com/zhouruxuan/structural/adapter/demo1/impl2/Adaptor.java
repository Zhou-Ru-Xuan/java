package com.zhouruxuan.structural.adapter.demo1.impl2;

import com.zhouruxuan.structural.adapter.demo1.common.Adaptee;
import com.zhouruxuan.structural.adapter.demo1.common.ITarget;

public class Adaptor implements ITarget {
    private Adaptee adaptee;

    public Adaptor(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void f1() {
        adaptee.fa(); //委托给Adaptee
    }

    public void f2() {
        //...重新实现f2()...
    }

    public void fc() {
        adaptee.fc();
    }
}