package com.zhouruxuan.behavioral.momento.demo2.commands;

public interface Command {
    String getName();
    void execute();
}