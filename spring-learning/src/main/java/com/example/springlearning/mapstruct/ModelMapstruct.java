package com.example.springlearning.mapstruct;

import com.example.springlearning.entity.pojo.Person;
import com.example.springlearning.entity.pojo.PersonMapstructModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModelMapstruct {
    ModelMapstruct INSTANCE = Mappers.getMapper(ModelMapstruct.class);

    @Mapping(target = "vPoiInPromiseBlackList", source = "vPoiInPromiseBlackList")
    PersonMapstructModel map(Person person);
}
