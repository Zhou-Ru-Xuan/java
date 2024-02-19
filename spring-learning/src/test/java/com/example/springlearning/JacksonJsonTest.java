package com.example.springlearning;

import com.example.springlearning.entity.JsonTestModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class JacksonJsonTest {

    /**
     * 验证fastjson导致字段名称变化的规律
     *
     * @throws JsonProcessingException
     */
    @Test
    public void test() throws JsonProcessingException {
        JsonTestModel person = new JsonTestModel();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(person);
        System.out.println(jsonString);
    }
}
