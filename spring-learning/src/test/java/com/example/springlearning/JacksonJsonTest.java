package com.example.springlearning;

import com.example.springlearning.entity.JsonTestModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class JacksonJsonTest {
    @Test
    public void test() throws JsonProcessingException {
        JsonTestModel person = new JsonTestModel();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(person);
        System.out.println(jsonString);
    }
}
