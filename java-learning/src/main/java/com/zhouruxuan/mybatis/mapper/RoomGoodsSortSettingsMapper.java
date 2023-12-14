package com.zhouruxuan.mybatis.mapper;


import com.zhouruxuan.mybatis.entity.RoomGoodsSortSettingsDO;
import org.apache.ibatis.annotations.Param;

public interface RoomGoodsSortSettingsMapper {

    int insertOrUpdate(RoomGoodsSortSettingsDO record);

    RoomGoodsSortSettingsDO selectByVpoiUser(@Param("poiId") Long poiId, @Param("partnerId") Long partnerId, @Param("userId") Integer userId);

    long count();
}
