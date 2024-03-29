package com.zhouruxuan.api.mapstruct.mapper;

import com.zhouruxuan.api.mapstruct.entity.BugSource;
import com.zhouruxuan.api.mapstruct.entity.BugTarget;
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
    //@Mapping(target = "bId", source = "bid")
    @Mapping(target = "bId",source = "bugSource.bugSource.bid")
    BugTarget gmapB(BugSource bugSource);
}
