package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UserRepositoryTest {
    @Autowired
    private UserRepository repository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void findAll() {
        List<User> result = repository.findAll();
        assertEquals(6, result.size());
    }

    @Test
    void save() {
        User user = new User();
        user.setName("user7");
        repository.save(user);

        List<User> result = repository.findAll();
        int count = JdbcTestUtils.countRowsInTable(jdbcTemplate, "user");
        assertEquals(7, result.size());
        assertEquals(7, count);
    }

    @Test
    void findByUsername() {
        List<User> result = repository.findByUsername("user1");
        assertEquals(1, result.size());
        assertEquals("user1", result.get(0).getName());
    }

    @Test
    void queryMethods() {
        List<User> result = repository.findByName("user1");
        assertEquals(1, result.size());
        assertEquals("user1", result.get(0).getName());

        result = repository.findByNameLike("user%");
        assertEquals(6, result.size());

        result = repository.findByNameStartingWith("user");
        assertEquals(6, result.size());

        result = repository.findByNameEndingWith("2");
        assertEquals(1, result.size());
        assertEquals("user2", result.get(0).getName());

        result = repository.findByNameContaining("user");
        assertEquals(6, result.size());

        result = repository.findByIdLessThan(4);
        assertEquals(3, result.size());

        result = repository.findByIdGreaterThan(4);
        assertEquals(2, result.size());

        result = repository.findByEnabledTrue();
        assertEquals(6, result.size());

        result = repository.findByEnabledFalse();
        assertEquals(0, result.size());
    }
}
