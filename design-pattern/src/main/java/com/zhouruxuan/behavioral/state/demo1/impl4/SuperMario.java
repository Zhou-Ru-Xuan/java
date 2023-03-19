package com.zhouruxuan.behavioral.state.demo1.impl4;

import com.zhouruxuan.behavioral.state.demo1.State;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-13
 **/
public class SuperMario implements IMario {
    private static final SuperMario instance = new SuperMario();

    private SuperMario() {
    }

    public static IMario getInstance() {
        return instance;
    }

    @Override
    public State getName() {
        return State.SUPER;
    }

    @Override
    public void obtainMushRoom(MarioStateMachine stateMachine) {

    }

    @Override
    public void obtainCape(MarioStateMachine stateMachine) {

    }

    @Override
    public void obtainFireFlower(MarioStateMachine stateMachine) {

    }

    @Override
    public void meetMonster(MarioStateMachine stateMachine) {

    }
}
