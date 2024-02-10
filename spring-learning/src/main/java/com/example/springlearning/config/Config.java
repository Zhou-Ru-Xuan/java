package com.example.springlearning.config;

import com.example.springlearning.entity.file.FileProcessor;
import com.example.springlearning.entity.file.FileReader;
import com.example.springlearning.entity.file.FileWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;


@Configuration
@ComponentScan("com.example.springlearning.entity")
public class Config {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    @DependsOn({"fileReader", "fileWriter"})
    public FileProcessor fileProcessor() {
        return new FileProcessor();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public FileReader fileReader() {
        return new FileReader();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public FileWriter fileWriter() {
        return new FileWriter();
    }
}