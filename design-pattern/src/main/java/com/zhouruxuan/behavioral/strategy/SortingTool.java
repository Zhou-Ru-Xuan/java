package com.zhouruxuan.behavioral.strategy;

import com.zhouruxuan.behavioral.strategy.impl1.Sorter;

public class SortingTool {
  public static void main(String[] args) {
    Sorter sorter = new Sorter();
    sorter.sortFile(args[0]);
  }
}