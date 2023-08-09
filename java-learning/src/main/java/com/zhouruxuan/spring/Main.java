package com.zhouruxuan.spring;

import com.zhouruxuan.spring.bean.Person;

import javax.annotation.Resource;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-05-01
 **/
public class Main {

    @Resource
    public Person person;

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.person);
    }
}
