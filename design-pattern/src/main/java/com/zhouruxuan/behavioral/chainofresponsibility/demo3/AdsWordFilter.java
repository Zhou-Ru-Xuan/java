package com.zhouruxuan.behavioral.chainofresponsibility.demo3;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-12
 **/
public class AdsWordFilter implements SensitiveWordFilter {
    @Override
    public boolean doFilter(Content content) {
        return false;
    }
}
