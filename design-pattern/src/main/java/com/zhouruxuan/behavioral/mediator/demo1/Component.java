package com.zhouruxuan.behavioral.mediator.demo1;

/**
 * Common component interface.
 */
public interface Component {
    void setMediator(Mediator mediator);
    String getName();
}