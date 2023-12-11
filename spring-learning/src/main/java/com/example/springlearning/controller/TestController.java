package com.example.springlearning.controller;


import com.example.springlearning.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class TestController {

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
    public void testPerson(@RequestBody Person person) {
        LOGGER.info("poiId ={}", person.toString());
    }
}
