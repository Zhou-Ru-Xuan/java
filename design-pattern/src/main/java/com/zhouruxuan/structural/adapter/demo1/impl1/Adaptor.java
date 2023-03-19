package com.zhouruxuan.structural.adapter.demo1.impl1;

import com.zhouruxuan.structural.adapter.demo1.common.Adaptee;
import com.zhouruxuan.structural.adapter.demo1.common.ITarget;

public class Adaptor extends Adaptee implements ITarget {
    public void f1() {
        super.fa();
    }

    public void f2() {
        //...重新实现f2()...
    }

    // 这里fc()不需要实现，直接继承自Adaptee，这是跟对象适配器最大的不同点
}