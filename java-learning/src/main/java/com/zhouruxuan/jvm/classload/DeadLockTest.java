package com.zhouruxuan.jvm.classload;

public class DeadLockTest {

  public static void main(String[] args) {
    try {
      Class.forName("com.zhouruxuan.jvm.classload.StaticA");
      Thread.sleep(1000);
      Class.forName("com.zhouruxuan.jvm.classload.StaticB");
    } catch (ClassNotFoundException | InterruptedException e) {
      e.printStackTrace();
    }
  }

}



