package com.zhouruxuan.structural.decorator.demo1.decorators;

public interface DataSource {
    void writeData(String data);

    String readData();
}