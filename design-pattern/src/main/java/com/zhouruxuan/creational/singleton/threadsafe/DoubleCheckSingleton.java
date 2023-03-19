package com.zhouruxuan.creational.singleton.threadsafe;

public class DoubleCheckSingleton {

    private static volatile DoubleCheckSingleton instance;

    private DoubleCheckSingleton() {
    }

    public static DoubleCheckSingleton getInstance() {
        DoubleCheckSingleton result = instance;
        if (result == null) {
            synchronized (DoubleCheckSingleton.class) {
                result = instance;
                if (result == null)
                    instance = result = new DoubleCheckSingleton();
            }
        }
        return result;
    }

}