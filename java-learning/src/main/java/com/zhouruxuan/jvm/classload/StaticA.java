package com.zhouruxuan.jvm.classload;

class StaticA {

  static {
    System.out.println("---StaticA clinit start");
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
    }
    System.out.println("---开始争夺StaticB资源");
    try {
      Class.forName("com.zhouruxuan.jvm.classload.StaticB");
    } catch (ClassNotFoundException ignored) {
    }
    System.out.println("---夺得StaticB资源");
    System.out.println("---StaticA clinit over");
  }
}