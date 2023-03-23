package com.zhouruxuan.behavioral.strategy.demo1;

import com.zhouruxuan.behavioral.strategy.demo1.impl1.Sorter;

public class SortingTool {
  public static void main(String[] args) {
    Sorter sorter = new Sorter();
    sorter.sortFile(args[0]);
  }
}