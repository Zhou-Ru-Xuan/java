package com.zhouruxuan.jvm.method.symbolicreference.nonIface;

public class Man extends Person {
  //public static int eat(int num) {
  //  System.out.println("man eat");
  //  return num;
  //}

  public static void main(String[] args) {
    Man.eat(12);
  }
}