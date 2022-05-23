package com.ct.test;

import com.ct.test.bean.User;
import com.ct.test.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class TestApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() throws SQLException {
        User user = new User();
        user.setNo(3);
        user.setAge(18);
        user.setBirth("2021-08-01");
        user.setSex("男");
        user.setName("亚索");
        userMapper.insert(user);
    }
}
