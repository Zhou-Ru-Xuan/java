package com.example.springlearning.controller;

import com.example.springlearning.entity.pojo.School;
import com.example.springlearning.entity.pojo.User;
import com.example.springlearning.enums.Enums;
import com.example.springlearning.result.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @GetMapping("/queryUserById/{id}")
    public ApiResult<User> queryUserById(@PathVariable Integer id) {
        return new ApiResult<>(Enums.SUCCESS, new User(1L, "name", 20, new School(), "aName"));
    }
}
