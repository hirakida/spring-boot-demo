package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@JdbcTest
public class UserRepositoryTest {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    private UserRepository repository;

    @BeforeEach
    public void init() {
        repository = new UserRepository(jdbcTemplate);
    }

    @Test
    public void findAll() {
        List<User> result = repository.findAll();
        assertEquals(result.size(), 5);
    }
}
