package com.example.springlearning;

import com.example.springlearning.dao.PersonDao;
import com.example.springlearning.entity.Person;
import com.example.springlearning.service.StudentService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        Person personById = personDao.getPersonById(123L);
        LOGGER.info(personById.toString());
    }
}
