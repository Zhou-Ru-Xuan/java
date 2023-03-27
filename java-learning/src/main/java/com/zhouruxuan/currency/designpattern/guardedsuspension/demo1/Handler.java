package com.zhouruxuan.currency.designpattern.guardedsuspension.demo1;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-27
 **/
public class Handler {
    //该方法可以发送消息
    void send(Message msg) {
        //省略相关代码
    }

    //MQ消息返回后会调用该方法
    //该方法的执行线程不同于
    //发送消息的线程
    void onMessage(Message msg) {
        //省略相关代码
    }

    //处理浏览器发来的请求
    Response handleWebReq() {
        //创建一消息
        Message msg1 = new Message("1", "{...}");
        //发送消息
        send(msg1);
        //如何等待MQ返回的消息呢？
        //String result = ?;

        return null;
    }
}
