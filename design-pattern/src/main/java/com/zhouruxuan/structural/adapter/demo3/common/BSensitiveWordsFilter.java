package com.zhouruxuan.structural.adapter.demo3.common;

public class BSensitiveWordsFilter  { // B敏感词过滤系统提供的接口
  public String filter(String text) {
    //...
    return this.getClass().getSimpleName();
  }
}