package com.example.springlearning.controller;


import com.example.springlearning.annotation.ShowMethodName;
import com.example.springlearning.entity.pojo.Person;
import com.example.springlearning.result.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class TestController implements ApplicationContextAware {
    public ApplicationContext applicationContext;

    @PostMapping("/test")
    public void testMethod(@RequestBody Map<String, Object> requestBody) {
        String poiId = (String) requestBody.get("poiId");
        Long partnerId = Long.valueOf(requestBody.get("partnerId").toString());
        List<Person> personList = (List<Person>) requestBody.get("persons");
        Person person = (Person) (requestBody.get("person"));

        LOGGER.info("poiId ={}", poiId);
        LOGGER.info("partnerId ={}", partnerId);
        LOGGER.info("personList ={}", personList);
        LOGGER.info("person={}", person.toString());
    }

    @PostMapping("/testPerson")
    @ShowMethodName
    public ApiResult<Boolean> testPerson(@RequestBody Person person) {
        LOGGER.info("person={} age={}", person.toString(), person.getAge());
        applicationContext.getBean(TestController.class).updatePersonName();
        // updatePersonName(); //切面失效，因为可以直接内部调用，不会经过代理
        return new ApiResult<>(200, null, true);
    }

    @ShowMethodName
    public void updatePersonName() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
