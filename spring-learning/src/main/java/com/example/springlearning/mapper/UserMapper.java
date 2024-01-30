package com.example.springlearning.mapper;

import com.example.springlearning.entity.pojo.User;

public interface UserMapper {
    int insertUser(User user);

    int updateUser(User user);

    int deleteUserById(Long id);

    User selectUserById(Long id);

    User selectLastUser();
}
