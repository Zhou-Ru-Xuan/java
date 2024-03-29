package com.zhouruxuan.api.mapstruct.mapper;

import com.zhouruxuan.api.mapstruct.entity.UserEntity;
import com.zhouruxuan.api.mapstruct.entity.UserPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-19
 **/
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserEntity po2entity(UserPo userPo);

}
