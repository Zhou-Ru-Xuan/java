package com.example.springlearning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan//如果没有指定 @MapperScan 的扫描路径，将从声明该注解的类的包开始进行扫描。
public class SpringLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLearningApplication.class, args);
    }

}
