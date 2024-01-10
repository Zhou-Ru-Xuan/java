package com.zhouruxuan.proxy;

import service.UserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserProxyHandler implements InvocationHandler {
    private UserService target;

    public UserProxyHandler(UserService target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoking getUserInfo()");
        Object result = method.invoke(target, args);
        System.out.println(result);
        System.out.println("After invoking getUserInfo()");
        return result;
    }
}
