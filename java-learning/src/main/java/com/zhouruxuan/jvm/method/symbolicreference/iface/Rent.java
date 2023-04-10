package com.zhouruxuan.jvm.method.symbolicreference.iface;

public interface Rent {

  default void rent() {
    System.out.println("parent rent");
  }
}




