package com.example.springlearning.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {
    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void repositoryClassMethods() {
    }

    @Around("repositoryClassMethods()")
    public Object measureMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        Object retval = pjp.proceed();
        long end = System.nanoTime();
        String methodName = pjp.getSignature().getName();
        LOGGER.info("Execution of " + methodName + " took " + TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");
        return retval;
    }

    @Before("execution(* com.example.springlearning.controller.*.*(..))")
    public void beforeMethod3() {
        LOGGER.info("beforeMethod3");
    }
}