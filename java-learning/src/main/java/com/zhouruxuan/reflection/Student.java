package com.zhouruxuan.reflection;

public class Student {
  private String name;
  private int age;

  public Student(){
    System.out.println("构造方法");
  }

  private Student(int age){
    this.age = age;
    System.out.println(age);
  }

  public void print(int num) {
    System.out.println("第" + num + "次，测试输出");
  }
}