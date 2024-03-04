package com.example.springlearning.controller;

import com.example.springlearning.enums.Enums;
import com.example.springlearning.result.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: RedisController
 * @description:
 * @author: sh.Liu
 * @date: 2022-03-08 14:28
 */
@RestController
@RequestMapping()
public class RedisController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("save")
    public ApiResult<Boolean> save(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return new ApiResult<>();
    }

    @GetMapping("get")
    public ApiResult<String> get(String key) {
        return new ApiResult<>(Enums.SUCCESS, redisTemplate.opsForValue().get(key));
    }
}
