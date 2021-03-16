package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest extends AbstractContainerInitializer {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAll() {
        List<User> result = userRepository.findAll();
        assertEquals(5, result.size());
    }

    @Test
    public void findByName() {
        List<User> result = userRepository.findByName("user1");
        assertEquals(1, result.size());
        assertEquals("user1", result.get(0).getName());
    }
}
