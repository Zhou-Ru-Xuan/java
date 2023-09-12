package com.zhouruxuan.io;

import org.junit.Test;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-20
 **/
public class PathTest {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir")); ///Users/zhouruxuan/Documents/code/java/java/
    }

    @Test
    public void testUserDir(){
        System.out.println(System.getProperty("user.dir")); ///Users/zhouruxuan/Documents/code/java/java/java-learning

    }
}
