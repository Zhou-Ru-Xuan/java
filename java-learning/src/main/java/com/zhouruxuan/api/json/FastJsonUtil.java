package com.zhouruxuan.api.json;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class FastJsonUtil {
    public static JSONObject parseJson(String jsonString) {
        return JSON.parseObject(jsonString);
    }

    public static void main(String[] args) {
        String jsonString = "{\"id\":1,\"name\":\"张三\",\"age\":18}";
        Person person = JSON.parseObject(jsonString, Person.class);
        System.out.println("ID：" + person.getId() + "，姓名：" + person.getName());

        String jsonStringArr = "[{\"id\":1,\"name\":\"张三\",\"age\":18},{\"id\":2,\"name\":\"李四\",\"age\":20}]";
        List<Person> personList = JSON.parseArray(jsonStringArr, Person.class);
        for (Person person2 : personList) {
            System.out.println("ID：" + person2.getId() + "，姓名：" + person2.getName());
        }
    }
}

@Getter
@Setter
class Person {
    private int id;
    private String name;
}
