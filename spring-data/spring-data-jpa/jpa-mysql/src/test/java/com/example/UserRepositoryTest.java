package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Import(DataSourceTestConfig.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAllTest() {
        List<User> result = userRepository.findAll();
        assertEquals(5, result.size());
    }

    @Test
    public void findByIdTest() {
        Optional<User> result = userRepository.findById(1);
        assertTrue(result.isPresent());
        assertEquals("user1", result.get().getName());
    }
}
