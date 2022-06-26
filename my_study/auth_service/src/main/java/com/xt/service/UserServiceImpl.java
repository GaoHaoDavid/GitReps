package com.xt.service;

import com.xt.entity.User;
import com.xt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl {

    @Autowired
    private UserMapper userMapper;

    public User selectUserByName(String name){
        return userMapper.selectByName(name);
    }
}
