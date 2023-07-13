package com.zhouruxuan.spring;

import com.zhouruxuan.spring.transaction.MyService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-05-01
 **/
public class Main {

    private MyService myService = new MyService();

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("");
        Object bean = classPathXmlApplicationContext.getBean("");

    }
}
