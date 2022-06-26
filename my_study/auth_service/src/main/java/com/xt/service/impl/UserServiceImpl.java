package com.xt.service.impl;

import com.xt.entity.LoginUser;
import com.xt.entity.User;
import com.xt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Objects;


@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    public User selectUserByName(String name){
        return userMapper.selectByName(name);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        User user = userMapper.selectByName(username);

        //用户查询失败则抛出异常
        if(Objects.isNull(user))
            throw new RuntimeException("用户名或者密码错误！");

        //TODO 查询对应的权限信息

        return new LoginUser(user);
    }
}
