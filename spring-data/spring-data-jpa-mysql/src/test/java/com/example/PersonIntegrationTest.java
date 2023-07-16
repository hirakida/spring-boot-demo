package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:/testdata.sql")
@Testcontainers
class PersonIntegrationTest {
    @Container
    @ServiceConnection
    private static final MySQLContainer<?> CONTAINER =
            new MySQLContainer<>("mysql:8.0").withEnv("TZ", "Asia/Tokyo");
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void findAll() {
        Person[] actual = restTemplate.getForObject("/persons", Person[].class);
        assertEquals(10, actual.length);
    }

    @Test
    void findById() {
        Person expected = new Person();
        expected.setId(6);
        expected.setName("user6");
        expected.setCreatedAt(LocalDateTime.parse("2020-12-01T00:00:00"));
        expected.setUpdatedAt(LocalDateTime.parse("2020-12-01T00:00:00"));

        Person actual = restTemplate.getForObject("/persons/{id}", Person.class, 6);
        assertEquals(expected, actual);
    }
}
