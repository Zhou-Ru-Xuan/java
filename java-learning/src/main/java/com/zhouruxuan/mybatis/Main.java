package com.zhouruxuan.mybatis;

import com.zhouruxuan.mybatis.entity.RoomGoodsSortSettingsDO;
import com.zhouruxuan.mybatis.mapper.RoomGoodsSortSettingsMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class Main {
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
    private static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testInsertAndSelect() {
        RoomGoodsSortSettingsDO roomGoodsSortSettingsDO = new RoomGoodsSortSettingsDO(null, 111111L, 222222222L, 1, 135613, "zhouruxuan", roomGoodsWeight, goodsAutoSort, null, null);
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            RoomGoodsSortSettingsMapper mapper = session.getMapper(RoomGoodsSortSettingsMapper.class);
            mapper.insertOrUpdate(roomGoodsSortSettingsDO);
            RoomGoodsSortSettingsDO roomGoodsSortSettingsDO1 = mapper.selectByVpoiUser(roomGoodsSortSettingsDO.getPoiId(), roomGoodsSortSettingsDO.getPartnerId(), roomGoodsSortSettingsDO.getUserId());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getPoiId(), roomGoodsSortSettingsDO1.getPoiId());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getPartnerId(), roomGoodsSortSettingsDO1.getPartnerId());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getUserId(), roomGoodsSortSettingsDO1.getUserId());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getRoomGoodsWeight(), roomGoodsSortSettingsDO1.getRoomGoodsWeight());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getGoodsAutoSort(), roomGoodsSortSettingsDO1.getGoodsAutoSort());
        }
    }

    @Test
    public void testInsertNull() {
        RoomGoodsSortSettingsDO roomGoodsSortSettingsDO = new RoomGoodsSortSettingsDO(null, 1L, 1L, 1, 1, "zhouruxuan1", "[]", null, null, null);
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            RoomGoodsSortSettingsMapper mapper = session.getMapper(RoomGoodsSortSettingsMapper.class);
            mapper.insertOrUpdate(roomGoodsSortSettingsDO);
            RoomGoodsSortSettingsDO roomGoodsSortSettingsDO1 = mapper.selectByVpoiUser(roomGoodsSortSettingsDO.getPoiId(), roomGoodsSortSettingsDO.getPartnerId(), roomGoodsSortSettingsDO.getUserId());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getPoiId(), roomGoodsSortSettingsDO1.getPoiId());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getPartnerId(), roomGoodsSortSettingsDO1.getPartnerId());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getUserId(), roomGoodsSortSettingsDO1.getUserId());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getRoomGoodsWeight(), roomGoodsSortSettingsDO1.getRoomGoodsWeight());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getGoodsAutoSort(), roomGoodsSortSettingsDO1.getGoodsAutoSort());
        }
    }

    @Test
    public void testQueryParamNull() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            RoomGoodsSortSettingsMapper mapper = session.getMapper(RoomGoodsSortSettingsMapper.class);
            RoomGoodsSortSettingsDO roomGoodsSortSettingsDO1 = mapper.selectByVpoiUser(null, null, null);
            Assertions.assertNull(roomGoodsSortSettingsDO1);
        }
    }

    @Test
    public void testInsertOnDuplicatedKeyAndUpdate() {
        RoomGoodsSortSettingsDO roomGoodsSortSettingsDO = new RoomGoodsSortSettingsDO(null, 2L, 2L, 2, 2, "zhouruxuan2", null, null, null, null);
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            RoomGoodsSortSettingsMapper mapper = session.getMapper(RoomGoodsSortSettingsMapper.class);
            mapper.insertOrUpdate(roomGoodsSortSettingsDO);
            RoomGoodsSortSettingsDO roomGoodsSortSettingsDO1 = mapper.selectByVpoiUser(roomGoodsSortSettingsDO.getPoiId(), roomGoodsSortSettingsDO.getPartnerId(), roomGoodsSortSettingsDO.getUserId());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getPoiId(), roomGoodsSortSettingsDO1.getPoiId());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getPartnerId(), roomGoodsSortSettingsDO1.getPartnerId());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getUserId(), roomGoodsSortSettingsDO1.getUserId());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getRoomGoodsWeight(), roomGoodsSortSettingsDO1.getRoomGoodsWeight());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getGoodsAutoSort(), roomGoodsSortSettingsDO1.getGoodsAutoSort());


            roomGoodsSortSettingsDO.setRoomGoodsWeight(roomGoodsWeight);
            roomGoodsSortSettingsDO.setGoodsAutoSort(goodsAutoSort);
            mapper.insertOrUpdate(roomGoodsSortSettingsDO);
            RoomGoodsSortSettingsDO roomGoodsSortSettingsDO2 = mapper.selectByVpoiUser(roomGoodsSortSettingsDO.getPoiId(), roomGoodsSortSettingsDO.getPartnerId(), roomGoodsSortSettingsDO.getUserId());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getPoiId(), roomGoodsSortSettingsDO2.getPoiId());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getPartnerId(), roomGoodsSortSettingsDO2.getPartnerId());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getUserId(), roomGoodsSortSettingsDO2.getUserId());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getRoomGoodsWeight(), roomGoodsSortSettingsDO2.getRoomGoodsWeight());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getGoodsAutoSort(), roomGoodsSortSettingsDO2.getGoodsAutoSort());
        }
    }

    @Test
    public void testTinyInt() {
        RoomGoodsSortSettingsDO roomGoodsSortSettingsDO = new RoomGoodsSortSettingsDO(null, 3L, 3L, 127, 3, "zhouruxuan3", roomGoodsWeight, goodsAutoSort, null, null);
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            RoomGoodsSortSettingsMapper mapper = session.getMapper(RoomGoodsSortSettingsMapper.class);
            mapper.insertOrUpdate(roomGoodsSortSettingsDO);
            RoomGoodsSortSettingsDO roomGoodsSortSettingsDO1 = mapper.selectByVpoiUser(roomGoodsSortSettingsDO.getPoiId(), roomGoodsSortSettingsDO.getPartnerId(), roomGoodsSortSettingsDO.getUserId());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getPoiId(), roomGoodsSortSettingsDO1.getPoiId());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getPartnerId(), roomGoodsSortSettingsDO1.getPartnerId());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getUserId(), roomGoodsSortSettingsDO1.getUserId());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getRoomGoodsWeight(), roomGoodsSortSettingsDO1.getRoomGoodsWeight());
            Assertions.assertEquals(roomGoodsSortSettingsDO.getGoodsAutoSort(), roomGoodsSortSettingsDO1.getGoodsAutoSort());
        }
    }

}
