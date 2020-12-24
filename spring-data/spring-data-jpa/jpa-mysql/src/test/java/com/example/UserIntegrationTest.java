package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:/testdata.sql")
@Testcontainers
public class UserIntegrationTest {
    @Container
    private static final MySQLContainer<?> CONTAINER =
            new MySQLContainer<>("mysql:8.0").withEnv("TZ", "Asia/Tokyo");
    @Autowired
    private TestRestTemplate restTemplate;

    @DynamicPropertySource
    static void dataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", CONTAINER::getUsername);
        registry.add("spring.datasource.password", CONTAINER::getPassword);
    }

    @Test
    public void findAll() {
        User[] response = restTemplate.getForObject("/users", User[].class);
        assertEquals(10, response.length);
    }

    @Test
    public void findById() {
        User expected = new User();
        expected.setId(6);
        expected.setName("user6");
        expected.setCreatedAt(LocalDateTime.parse("2020-12-01T00:00:00"));
        expected.setUpdatedAt(LocalDateTime.parse("2020-12-01T00:00:00"));

        User response = restTemplate.getForObject("/users/{id}", User.class, 6);
        assertEquals(expected, response);
    }
}
