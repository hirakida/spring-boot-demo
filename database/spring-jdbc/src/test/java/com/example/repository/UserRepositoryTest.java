package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import com.example.model.User;

@JdbcTest
@Import(UserRepository.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void findAll() {
        List<User> result = repository.findAll();
        assertEquals(result.size(), 5);
    }
}
