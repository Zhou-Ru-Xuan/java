package com.zhouruxuan.behavioral.state.demo1.impl4;

import com.zhouruxuan.behavioral.state.demo1.State;

public interface IMario {
  State getName();
  void obtainMushRoom(MarioStateMachine stateMachine);
  void obtainCape(MarioStateMachine stateMachine);
  void obtainFireFlower(MarioStateMachine stateMachine);
  void meetMonster(MarioStateMachine stateMachine);
}