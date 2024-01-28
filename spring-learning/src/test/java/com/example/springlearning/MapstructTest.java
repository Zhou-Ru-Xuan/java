package com.example.springlearning;

import com.example.springlearning.entity.pojo.Person;
import com.example.springlearning.entity.pojo.PersonMapstructModel;
import com.example.springlearning.mapstruct.ModelMapstruct;
import org.junit.jupiter.api.Test;

public class MapstructTest {
    @Test
    public void test(){
        Person person = new Person();
        PersonMapstructModel map = ModelMapstruct.INSTANCE.map(person);
        System.out.println(map);
    }
}
