package com.daomeidan.userService.service;

import com.daomeidan.userService.mapper.UserMapper;
import com.daomeidan.userService.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryById(Long id) {
        return userMapper.findById(id);
    }
}