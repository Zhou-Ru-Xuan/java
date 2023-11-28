package com.zhouruxuan.api.mapstruct.mapper;

import com.zhouruxuan.api.mapstruct.entity.UserEntity;
import com.zhouruxuan.api.mapstruct.entity.UserEntity2;
import com.zhouruxuan.api.mapstruct.entity.UserEntity3;
import com.zhouruxuan.api.mapstruct.entity.UserPo;
import com.zhouruxuan.api.mapstruct.mapper.util.AttributeConvertUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AttributeConvertUtil.class)
public interface IPersonMapper {
    IPersonMapper INSTANCE = Mappers.getMapper(IPersonMapper.class);

    UserEntity po2entity(UserPo userPo);

    @Mapping(target = "userNick1", source = "userNick")
    UserEntity2 po2entity2(UserPo userPo);

    @Mapping(target = "attributes", source = "attributes", qualifiedByName = "jsonToObject")
    @Mapping(target = "userNick1", source = "userNick")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userVerified", defaultValue = "defaultValue")
    UserEntity3 po2entity3(UserPo userPo);


    @Mappings({
            @Mapping(target = "attributes", source = "attributes", qualifiedByName = "jsonToObject"),
            @Mapping(target = "userNick1", source = "userNick"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "userVerified", defaultValue = "defaultValue"),
    })
    UserEntity3 po2entity31(UserPo userPo);
}