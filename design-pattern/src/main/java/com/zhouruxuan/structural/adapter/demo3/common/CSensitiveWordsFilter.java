package com.zhouruxuan.structural.adapter.demo3.common;

public class CSensitiveWordsFilter { // C敏感词过滤系统提供的接口
  public String filter(String text, String mask) {
    //...
    return this.getClass().getSimpleName();
  }
}