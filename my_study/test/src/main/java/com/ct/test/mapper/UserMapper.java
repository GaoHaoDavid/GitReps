package com.ct.test.mapper;

import com.ct.test.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<User> selectAll();

    int insert(User user);
}
