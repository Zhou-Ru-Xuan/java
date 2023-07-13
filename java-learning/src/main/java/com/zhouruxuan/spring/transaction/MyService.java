package com.zhouruxuan.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class MyService {

    @Autowired
    private AService aService;

    @Autowired
    private BService bService;

    @Transactional
    public String doSomething() {
        try {
            aService.doA();
        } catch (Exception e) {
            // a函数执行失败，回滚事务并返回a函数失败的信息
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "a函数执行失败：" + e.getMessage();
        }

        try {
            bService.doB();
        } catch (Exception e) {
            // b函数执行失败，回滚事务并返回b函数失败的信息
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "b函数执行失败：" + e.getMessage();
        }

        // a函数和b函数都执行成功，提交事务
        return "a函数和b函数都执行成功";
    }
}
