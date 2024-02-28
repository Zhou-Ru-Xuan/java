package com.example.springlearning.aop;

import com.example.springlearning.result.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Aspect
@Component
@Slf4j
public class ValidationAspect {
    private static Validator validator;

    static {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Around("execution(* com.example.springlearning.controller.*.*(..))")
    public Object beforeMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("beforeMethod1");
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            Set<ConstraintViolation<Object>> violations = validator.validate(arg);
            if (!violations.isEmpty()) {
                return new ApiResult<>(-1,violations.iterator().next().getMessage());
            }
        }
        return joinPoint.proceed();
    }

    @Around("execution(* com.example.springlearning.controller.*.*(..))")
    public Object beforeMethod2(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("beforeMethod2");
        Thread.sleep(1000L);
        return joinPoint.proceed();
    }


}
