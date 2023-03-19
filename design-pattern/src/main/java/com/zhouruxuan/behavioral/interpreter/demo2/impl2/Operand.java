package com.zhouruxuan.behavioral.interpreter.demo2.impl2;

import java.util.Map;

interface Operand {
    double evaluate(Map<String, Integer> context);
    void traverse(int level);
}