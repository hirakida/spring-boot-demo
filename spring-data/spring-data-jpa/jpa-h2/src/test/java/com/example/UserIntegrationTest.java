package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;

import lombok.RequiredArgsConstructor;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = AutowireMode.ALL)
@RequiredArgsConstructor
public class UserIntegrationTest {
    private final TestRestTemplate restTemplate;

    @Test
    public void findById() {
        User user = new User();
        user.setId(1);
        user.setName("user1");
        user.setEnabled(true);
        LocalDateTime dateTime = LocalDateTime.of(2019, 1, 2, 12, 0);
        user.setCreatedAt(dateTime);
        user.setUpdatedAt(dateTime);

        User response = restTemplate.getForObject("/users/{id}", User.class, 1);
        assertNotNull(response);
        assertEquals(user, response);
    }
}
