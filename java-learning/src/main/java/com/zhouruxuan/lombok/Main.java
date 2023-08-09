package com.zhouruxuan.lombok;

public class Main {
    public static void main(String[] args) {
        BuilderTest build = BuilderTest.Builder.aBuilderTest().c(3).build();
        System.out.println(build);
    }
}
