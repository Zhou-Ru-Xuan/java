package com.zhouruxuan.mybatis.mapper;


import com.zhouruxuan.mybatis.entity.RoomGoodsSortSettingsDO;

public interface RoomGoodsSortSettingsMapper {

    int insertOrUpdate(RoomGoodsSortSettingsDO record);

    RoomGoodsSortSettingsDO selectByVpoiUser(RoomGoodsSortSettingsDO record);

    long count();
}
