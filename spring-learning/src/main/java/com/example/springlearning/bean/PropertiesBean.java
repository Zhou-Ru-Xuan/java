package com.example.springlearning.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PropertiesBean {
    @Value("${numberList}")
    private List<Integer> numberList;

    @Value("${numberList}")
    private Integer[] numberList2;

    @Value("${numberList}")
    private List<String> strNumberList;
}
