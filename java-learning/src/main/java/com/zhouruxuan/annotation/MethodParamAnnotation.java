package com.zhouruxuan.annotation;

import java.lang.annotation.*;

@Target(value = ElementType.PARAMETER)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface MethodParamAnnotation {
    String name() default "";
}
