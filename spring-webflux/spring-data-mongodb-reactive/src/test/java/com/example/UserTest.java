package com.example;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void createTest() {
        User user = new User("1", "name1", 30, LocalDateTime.now(), LocalDateTime.now());
        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals("1", user.getId());
    }
}
