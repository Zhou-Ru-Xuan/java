package com.example.springlearning.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ShowMethodNameAspect {
    @Before("@annotation(com.example.springlearning.annotation.ShowMethodName)")
    public void showMethodName(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        LOGGER.info("Method Name:{} ", methodName);
    }
}
