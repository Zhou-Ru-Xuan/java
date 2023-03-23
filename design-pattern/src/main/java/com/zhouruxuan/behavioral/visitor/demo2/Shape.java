package com.zhouruxuan.behavioral.visitor.demo2;

public interface Shape {
    void move(int x, int y);
    void draw();
    String accept(Visitor visitor);
}