package com.example.springlearning;

import com.example.springlearning.entity.Person;
import com.example.springlearning.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringLearningApplicationTests {
    @Autowired
    private Person person;
    @Autowired
    private StudentService studentService;

    @Test
    void contextLoads() {
        System.out.println(person.getNumberList().get(0));
        System.out.println(person.getNumberList2()[0]);
    }
}
