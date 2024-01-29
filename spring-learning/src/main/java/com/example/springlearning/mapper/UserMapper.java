package com.example.springlearning.mapper;

import com.example.springlearning.entity.pojo.User;

public interface UserMapper {
    void insertUser(User user);

    void updateUser(User user);

    void deleteUserById(Long id);

    User selectUserById(Long id);
}
