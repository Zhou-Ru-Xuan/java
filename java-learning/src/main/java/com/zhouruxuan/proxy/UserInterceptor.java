package com.zhouruxuan.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

// 自定义的方法拦截器
class UserInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("cglib 在方法执行前进行增强");
        Object result = proxy.invokeSuper(obj, args); // 调用原始方法
        System.out.println(result);
        System.out.println("cglib 在方法执行后进行增强");
        return result;
    }
}