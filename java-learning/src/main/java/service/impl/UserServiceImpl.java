package service.impl;

import service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public String getUserInfo() {
        return "User information";
    }
}