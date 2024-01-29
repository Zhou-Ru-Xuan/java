package com.example.springlearning;

import com.alibaba.fastjson.JSON;
import com.example.springlearning.entity.JsonTestModel;
import com.example.springlearning.entity.thrift.TModel;
import org.junit.jupiter.api.Test;

public class FastJsonTest {
    /**
     * 测试首字母小写第二个字母大写
     */
    @Test
    public void testTModel() {
        TModel tModel = new TModel();
        String jsonString = JSON.toJSONString(tModel);
        System.out.println(jsonString);


    }

    /**
     * 测试首字母小写第二个字母大写
     */
    @Test
    public void testJsonSerialization() {
        JsonTestModel jsonTestModel = new JsonTestModel();
        System.out.println(JSON.toJSONString(jsonTestModel));
    }
}
