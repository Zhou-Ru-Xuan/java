package com.zhouruxuan.annotation;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-24
 **/
public class AnnotationTest {
    @Hello("hello")
    public static void main(String[] args) throws NoSuchMethodException {
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        Class cls = AnnotationTest.class;
        Method method = cls.getMethod("main", String[].class);
        Hello hello = method.getAnnotation(Hello.class);
        System.out.println(hello.value());
    }

    /**
     * 通过注解读取参数
     */
    @org.junit.Test
    public void param_in_method_annotation() {
        for (Method method : AnnotationTest.class.getMethods()) {
            if (method.getName().equals("print")) {
                for (Parameter parameter : method.getParameters()) {
                    if (parameter.getAnnotation(MethodParamAnnotation.class).name().equals("goodsId")) {
                        System.out.println("goodsIdType = " + parameter.getType());
                    }
                    if (parameter.getAnnotation(MethodParamAnnotation.class).name().equals("roomId")) {
                        System.out.println("roomIdType = " + parameter.getType());
                    }
                }
            }
        }
    }


    public void print(@MethodParamAnnotation(name = "goodsId") Long goodsId, @MethodParamAnnotation(name = "roomId") Double roomId) {
        System.out.println(goodsId + "_" + roomId);
    }
}
