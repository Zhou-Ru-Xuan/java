package com.zhouruxuan.behavioral.chainofresponsibility.demo1.imp1;


public class HandlerA extends Handler {
  @Override
  public void handle() {
    boolean handled = false;
    //...
    if (!handled && successor != null) {
      successor.handle();
    }
  }
}