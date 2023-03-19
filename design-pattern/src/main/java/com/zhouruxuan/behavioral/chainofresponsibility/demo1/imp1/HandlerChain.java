package com.zhouruxuan.behavioral.chainofresponsibility.demo1.imp1;

/**
 * 如果任意一个handler能处理，那么将不会传递下去
 */
public class HandlerChain {
  private Handler head = null;
  private Handler tail = null;

  public void addHandler(Handler handler) {
    handler.setSuccessor(null);

    if (head == null) {
      head = handler;
      tail = handler;
      return;
    }

    tail.setSuccessor(handler);
    tail = handler;
  }

  public void handle() {
    if (head != null) {
      head.handle();
    }
  }
}