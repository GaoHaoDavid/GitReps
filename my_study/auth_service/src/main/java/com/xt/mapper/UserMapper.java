package com.xt.mapper;

import com.xt.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {


    @Select("select * from user where username=#{name}")
    User selectByName(String name);
}
