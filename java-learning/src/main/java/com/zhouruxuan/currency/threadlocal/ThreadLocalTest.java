package com.zhouruxuan.currency.threadlocal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ThreadLocalTest {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            ThreadLocal<Entity> threadLocal1 = new ThreadLocal<>();
            threadLocal1.set(new Entity("threadLocal1"));
            System.out.println(threadLocal1.get());
            ThreadLocal<Entity> threadLocal2 = new ThreadLocal<>();
            threadLocal2.set(new Entity("threadLocal2"));
            System.out.println(threadLocal2.get());
        });
        t.start();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Entity {
        String name;
    }

}

