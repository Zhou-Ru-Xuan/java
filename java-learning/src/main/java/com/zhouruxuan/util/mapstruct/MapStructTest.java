package com.zhouruxuan.util.mapstruct;


import com.zhouruxuan.util.mapstruct.entity.UserEntity;
import com.zhouruxuan.util.mapstruct.entity.UserEntity2;
import com.zhouruxuan.util.mapstruct.entity.UserEntity3;
import com.zhouruxuan.util.mapstruct.entity.UserPo;
import com.zhouruxuan.util.mapstruct.mapper.IPersonMapper;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class MapStructTest {
    public static void main(String[] args) {
        testNormal();
    }

    public static void testNormal() {
        UserPo userPo = UserPo.builder()
                .id(1L)
                .gmtCreate(new Date())
                .buyerId(666L)
                .userNick("测试mapstruct")
                .userVerified("ok")
                .age(18L)
                .build();
        System.out.println(userPo);
        UserEntity userEntity = IPersonMapper.INSTANCE.po2entity(userPo);
        System.out.println(userEntity);


    }

    @Test
    public void test2() {
        UserPo userPo = UserPo.builder()
                .id(1L)
                .gmtCreate(new Date())
                .buyerId(666L)
                .userNick("测试mapstruct")
                .userVerified("ok")
                .age(18L)
                .build();
        System.out.println(userPo);
        UserEntity2 userEntity2 = IPersonMapper.INSTANCE.po2entity2(userPo);
        System.out.println(userEntity2);
    }

    @Test
    public void test3() {
        String attributes =
                """
                        {"id":2,"name":"测试123"}""";
        UserPo userPo = UserPo.builder()
                .id(1L)
                .gmtCreate(new Date())
                .buyerId(666L)
                .userNick("测试mapstruct")
                .userVerified("ok")
                .age(18L)
                .attributes(attributes)
                .build();
        System.out.println(userPo);
        UserEntity3 userEntity3 = IPersonMapper.INSTANCE.po2entity3(userPo);
        System.out.println(userEntity3);
    }

    @Test
    public void test4() {
        int times = 50000000;
        final long springStartTime = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            UserPo userPo = UserPo.builder()
                    .id(1L)
                    .gmtCreate(new Date())
                    .buyerId(666L)
                    .userNick("测试123")
                    .userVerified("ok")
                    .build();
            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(userPo, userEntity);
        }
        final long springEndTime = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            UserPo userPo = UserPo.builder()
                    .id(1L)
                    .gmtCreate(new Date())
                    .buyerId(666L)
                    .userNick("测试123")
                    .userVerified("ok")
                    .build();
            UserEntity userEntity = IPersonMapper.INSTANCE.po2entity(userPo);
        }
        final long mapstructEndTime = System.currentTimeMillis();
        System.out.println("BeanUtils use time=" + (springEndTime - springStartTime) / 1000 + "秒" +
                "; Mapstruct use time=" + (mapstructEndTime - springEndTime) / 1000 + "秒");
    }

    @Test
    public void test5(){

    }

}

