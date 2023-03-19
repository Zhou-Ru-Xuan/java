package com.zhouruxuan.structural.adapter.demo3.impl2;

// 使用适配器模式进行改造
public interface ISensitiveWordsFilter { // 统一接口定义
    String filter(String text);
}