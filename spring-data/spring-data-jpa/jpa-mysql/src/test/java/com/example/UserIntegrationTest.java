package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
public class UserIntegrationTest {
    @Container
    private static final MySQLContainer<?> container = new MySQLContainer<>("mysql:8.0");
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @DynamicPropertySource
    static void dataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @BeforeEach
    public void init() {
        ScriptUtils.runInitScript(new JdbcDatabaseDelegate(container, ""), "schema.sql");
        ScriptUtils.runInitScript(new JdbcDatabaseDelegate(container, ""), "data.sql");
    }

    @Test
    public void findAll() {
        User[] response = restTemplate.getForObject("http://localhost:" + port + "/users", User[].class);
        List<User> users = List.of(response);
        assertEquals(users.size(), 5);
    }
}
