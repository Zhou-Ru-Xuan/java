package com.zhouruxuan.behavioral.momento.demo1.impl2;

import java.util.Stack;

public class Snapshot {
  private String text;

  public Snapshot(String text) {
    this.text = text;
  }

  public String getText() {
    return this.text;
  }
}

