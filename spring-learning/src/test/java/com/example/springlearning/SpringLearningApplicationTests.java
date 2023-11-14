package com.example.springlearning;

import com.example.springlearning.dao.PersonDao;
import com.example.springlearning.entity.Person;
import com.example.springlearning.entity.file.FileProcessor;
import com.example.springlearning.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
class SpringLearningApplicationTests {
    @Autowired
    private Person person;

    @Autowired
    private StudentService studentService;
    @Resource
    private PersonDao personDao;

    @Resource
    private FileProcessor fileProcessor;

    @Test
    void test() {
        Person personById = personDao.getPersonById(123L);
        LOGGER.info(personById.toString());
    }

    @Test
    public void WhenFileProcessorIsCreated_FileTextContains_Processed() {
        assertTrue(fileProcessor.process().endsWith("processed"));
    }
}
