package com.zhouruxuan.behavioral.observer.demo3.listener;

import java.io.File;

public interface EventListener {
    void update(String eventType, File file);
}