package com.zhouruxuan.lombok;

import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public class LombokEntity extends LombokEntityFather {
    private int a;
    private String b;
    private Object c;
    private List<Object> objects;

}
