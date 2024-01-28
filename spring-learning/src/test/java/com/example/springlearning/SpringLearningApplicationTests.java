package com.example.springlearning;

import com.example.springlearning.dao.PersonDao;
import com.example.springlearning.entity.pojo.Person;
import com.example.springlearning.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class SpringLearningApplicationTests {
    @Autowired
    private Person person;

    @Autowired
    private StudentService studentService;
    @Resource
    private PersonDao personDao;

    @Test
    void test() {
        LOGGER.info("person={}", person.toString());
    }
}
