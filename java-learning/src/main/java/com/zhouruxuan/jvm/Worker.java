package com.zhouruxuan.jvm;

import lombok.Getter;

@Getter
public class Worker {

  private String name;
  private double money;

  public Worker() {
  }

  public Worker(String name) {
    this.name = name;
  }

  public void makeMoney() {
    money++;
  }
}



