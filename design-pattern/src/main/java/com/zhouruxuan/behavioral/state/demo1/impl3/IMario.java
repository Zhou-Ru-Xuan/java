package com.zhouruxuan.behavioral.state.demo1.impl3;

import com.zhouruxuan.behavioral.state.demo1.State;

public interface IMario { //所有状态类的接口
  State getName();
  //以下是定义的事件
  void obtainMushRoom();
  void obtainCape();
  void obtainFireFlower();
  void meetMonster();
}