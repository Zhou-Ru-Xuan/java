package com.zhouruxuan.behavioral.state.demo1.impl1;

import com.zhouruxuan.behavioral.state.demo1.State;

public class MarioStateMachine {
  private int score;
  private State currentState;

  public MarioStateMachine() {
    this.score = 0;
    this.currentState = State.SMALL;
  }

  /**
   * 小马里奥吃蘑菇变成超级马里奥，积分+100
   */
  public void obtainMushRoom() {
    if (currentState.equals(State.SMALL)) {
      this.currentState = State.SUPER;
      this.score += 100;
    }
  }

  /**
   * 小马里奥或者超级马里奥吃斗篷会变成斗篷马里奥，积分+200
   */
  public void obtainCape() {
    if (currentState.equals(State.SMALL) || currentState.equals(State.SUPER) ) {
      this.currentState = State.CAPE;
      this.score += 200;
    }
  }

  /**
   * 小马里奥或者超级马里奥吃火焰可以变成火焰马里奥，积分+300
   */
  public void obtainFireFlower() {
    if (currentState.equals(State.SMALL) || currentState.equals(State.SUPER) ) {
      this.currentState = State.FIRE;
      this.score += 300;
    }
  }

  /**
   * 超级马里奥吃怪兽会变成小马里奥，积分-100
   * 斗篷马里奥-200
   * 火焰马里奥-300
   */
  public void meetMonster() {
    if (currentState.equals(State.SUPER)) {
      this.currentState = State.SMALL;
      this.score -= 100;
      return;
    }

    if (currentState.equals(State.CAPE)) {
      this.currentState = State.SMALL;
      this.score -= 200;
      return;
    }

    if (currentState.equals(State.FIRE)) {
      this.currentState = State.SMALL;
      this.score -= 300;
      return;
    }
  }

  public int getScore() {
    return this.score;
  }

  public State getCurrentState() {
    return this.currentState;
  }
}