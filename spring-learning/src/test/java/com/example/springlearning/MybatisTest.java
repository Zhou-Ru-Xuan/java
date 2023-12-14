package com.example.springlearning;

import com.example.springlearning.entity.DO.RoomGoodsSortSettingsDO;
import com.example.springlearning.mapper.RoomGoodsSortSettingsMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MybatisTest {
    private static String roomGoodsWeight = "[\n" +
            "  {\"13356\":0},\n" +
            "  {\"321465\":1},\n" +
            "  {\"1234321\":2}\n" +
            "]";
    private static String goodsAutoSort = "[\n" +
            "    {\"type\":1,\n" +
            "     \"value\":0\n" +
            "     },\n" +
            "    {\"type\":2,\n" +
            "     \"value\":1\n" +
            "     },\n" +
            "    {\"type\":3,\n" +
            "     \"value\":0\n" +
            "     },\n" +
            "    {\"type\":4,\n" +
            "     \"value\":1\n" +
            "     },{\n" +
            "    \t\"type\":5,\n" +
            "      \"value\":0\n" +
            "    }\n" +
            "\t]";
    @Autowired
    private RoomGoodsSortSettingsMapper roomGoodsSortSettingsMapper;


    @Test
    public void testInsertAndSelect() {
        RoomGoodsSortSettingsDO roomGoodsSortSettingsDO = new RoomGoodsSortSettingsDO(null, 111111L, 222222222L, 1, 135613, "zhouruxuan", roomGoodsWeight, goodsAutoSort, null, null);
        roomGoodsSortSettingsMapper.insertOrUpdate(roomGoodsSortSettingsDO);
        RoomGoodsSortSettingsDO roomGoodsSortSettingsDO1 = roomGoodsSortSettingsMapper.selectByVpoiUser(roomGoodsSortSettingsDO.getPoiId(), roomGoodsSortSettingsDO.getPartnerId(), roomGoodsSortSettingsDO.getUserId());
        Assertions.assertEquals(roomGoodsSortSettingsDO.getPoiId(), roomGoodsSortSettingsDO1.getPoiId());
        Assertions.assertEquals(roomGoodsSortSettingsDO.getPartnerId(), roomGoodsSortSettingsDO1.getPartnerId());
        Assertions.assertEquals(roomGoodsSortSettingsDO.getUserId(), roomGoodsSortSettingsDO1.getUserId());
        Assertions.assertEquals(roomGoodsSortSettingsDO.getRoomGoodsWeight(), roomGoodsSortSettingsDO1.getRoomGoodsWeight());
        Assertions.assertEquals(roomGoodsSortSettingsDO.getGoodsAutoSort(), roomGoodsSortSettingsDO1.getGoodsAutoSort());
    }
}
