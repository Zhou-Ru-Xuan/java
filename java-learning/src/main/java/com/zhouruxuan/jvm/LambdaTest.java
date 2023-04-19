package com.zhouruxuan.jvm;

import java.util.stream.IntStream;

public class LambdaTest {

  public static void main(String[] args) {
    int num = 3;
    IntStream.of(1, 2, 3).map(i -> i * 2).map(i -> i * num).map(LambdaTest::add);
  }

  public static int add(int num) {
    return num + 3;
  }
}

