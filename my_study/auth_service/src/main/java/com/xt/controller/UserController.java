package com.xt.controller;

import com.xt.entity.ResultInfo;
import com.xt.entity.User;
import com.xt.service.impl.UserServiceImpl;
import com.xt.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("/getUserInfo")
    public User test(){
         return userService.selectUserByName("admin");
    }



    @PostMapping("/login")
    public ResultInfo login(@RequestBody HashMap<String,Object> map){

        if(null==map) return new ResultInfo(401,"请输入登录信息",null);

        String name= (String) map.get("username");
        User user = userService.selectUserByName(name);
        //1.验证用户信息
        if(user.getPassword().equals(map.get("pwd"))){
            //2.生成一个简单的令牌
            String token= UUID.randomUUID()+"";
            //3.将用户信息存入redis并设置过期时间
//            redisTemplate.opsForValue().set(token,user, Duration.ofMinutes(30L));
            redisUtil.add(token,user,30, TimeUnit.MINUTES);

            return new ResultInfo(200,"登录成功",token);
        }else
            return new ResultInfo(401,"用户信息错误",null);

    }
}
