package com.example.springlearning.mapper;


import com.example.springlearning.entity.DO.RoomGoodsSortSettingsDO;
import org.apache.ibatis.annotations.Param;

public interface RoomGoodsSortSettingsMapper {

    int insertOrUpdate(RoomGoodsSortSettingsDO record);

    RoomGoodsSortSettingsDO selectByVpoiUser(@Param("poiId") Long poiId, @Param("partnerId") Long partnerId, @Param("userId") Integer userId);

    long count();
}
