package com.zhouruxuan.proxy;

import net.sf.cglib.proxy.Enhancer;
import org.junit.jupiter.api.Test;
import service.UserService;
import service.impl.UserServiceImpl;

import java.lang.reflect.Proxy;

public class ProxyTest {
    @Test
    public void testJDKProxy() {
        UserService userService = new UserServiceImpl();
        UserProxyHandler handler = new UserProxyHandler(userService);

        UserService proxy = (UserService) Proxy.newProxyInstance(
                userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                handler
        );

        proxy.getUserInfo();
    }

    @Test
    public void testCGlibProxy() {
        // 创建Enhancer对象，用于生成代理类
        Enhancer enhancer = new Enhancer();
        // 设置目标类为父类
        enhancer.setSuperclass(UserServiceImpl.class);
        // 设置回调对象为自定义的方法拦截器
        enhancer.setCallback(new UserInterceptor());
        // 生成代理类
        UserService proxy = (UserService) enhancer.create();

        // 调用代理类的方法
        proxy.getUserInfo();
    }
}
