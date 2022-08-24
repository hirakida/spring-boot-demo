package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:/testdata.sql")
 class UserIntegrationTest extends AbstractContainerInitializer {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
     void findAll() {
        User[] response = restTemplate.getForObject("/users", User[].class);
        assertEquals(10, response.length);
    }

    @Test
     void findById() {
        User expected = new User();
        expected.setId(6);
        expected.setName("user6");
        expected.setCreatedAt(LocalDateTime.parse("2020-12-01T00:00:00"));
        expected.setUpdatedAt(LocalDateTime.parse("2020-12-01T00:00:00"));

        User response = restTemplate.getForObject("/users/{id}", User.class, 6);
        assertEquals(expected, response);
    }
}
