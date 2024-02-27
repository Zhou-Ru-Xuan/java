package com.example.springlearning;

import com.example.springlearning.entity.pojo.User;
import com.example.springlearning.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 测试插入用户
     */
    @Test
    public void testInsertUser() throws Throwable {
        // arrange
        User user = new User();
        String name = "name";
        user.setName(name);
        user.setAge(1);

        // act
        int insertNum = userMapper.insertUser(user);

        Assertions.assertEquals(1, insertNum);
    }

    /**
     * 测试更新用户
     */
    @Test
    public void testUpdateUser() throws Throwable {
        // arrange
        User user = new User();
        user.setId(1L);
        user.setName("test");

        // act
        userMapper.updateUser(user);

    }

    /**
     * 测试根据ID删除用户
     */
    @Test
    public void testDeleteUserById() throws Throwable {
        // arrange
        Long id = 1L;

        // act
        userMapper.deleteUserById(id);

    }

    /**
     * 测试根据ID查询用户
     */
    @Test
    public void testSelectUserById() throws Throwable {
        User lastUser = userMapper.selectLastUser();
        Assertions.assertNotNull(lastUser);
        // arrange
        Long id = lastUser.getId();
        // act
        User returnedUser = userMapper.selectUserById(id);
        Assertions.assertEquals(lastUser, returnedUser);
    }
}
