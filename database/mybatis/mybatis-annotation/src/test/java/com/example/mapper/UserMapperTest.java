package com.example.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.example.entity.User;

@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserMapperTest {
    @Autowired
    private UserMapper mapper;

    @Test
    public void get() {
        List<User> users = mapper.findAll(new RowBounds());
        assertEquals(5, users.size());
    }
}
