package com.zhouruxuan.creational.singleton.reflect;

public class EagerInitializedSingleton {

    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

    // private constructor to avoid client applications using the constructor
    private EagerInitializedSingleton(){
        /*
           反射破解单例模式需要添加的代码
        */
        //if(instance != null) {
        //    throw new RuntimeException("单例模式无法重复创建");
        //}
    }

    public static EagerInitializedSingleton getInstance() {
        return instance;
    }
}