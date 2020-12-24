package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void findAll() {
        List<User> result = userRepository.findAll();
        assertEquals(6, result.size());
    }

    @Test
    public void findByName() {
        List<User> result = userRepository.findByName("user1");
        assertEquals(1, result.size());
        assertEquals("user1", result.get(0).getName());
    }

    @Test
    public void findByNameStartingWith() {
        List<User> result = userRepository.findByNameStartingWith("user");
        assertEquals(6, result.size());
    }

    @Test
    public void save() {
        User user = new User();
        user.setName("user7");
        userRepository.save(user);

        List<User> result = userRepository.findAll();
        int count = JdbcTestUtils.countRowsInTable(jdbcTemplate, "user");
        assertEquals(7, result.size());
        assertEquals(7, count);
    }
}
