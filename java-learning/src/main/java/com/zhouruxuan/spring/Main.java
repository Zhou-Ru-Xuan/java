package com.zhouruxuan.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-05-01
 **/
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("");
        Object bean = classPathXmlApplicationContext.getBean("");

    }
}
