package com.zhouruxuan.util.mapstruct.mapper;

import com.zhouruxuan.util.mapstruct.entity.BugSource;
import com.zhouruxuan.util.mapstruct.entity.BugTarget;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-20
 **/
@Mapper
public interface BugMapper {
    BugMapper INSTANCE = Mappers.getMapper(BugMapper.class);

    @Mapping(target = "cid", source = "CId")
    @Mapping(target = "bId", source = "bid")
    BugTarget gmapB(BugSource bugSource);
}
