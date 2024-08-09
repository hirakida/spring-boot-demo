package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:/testdata.sql")
class UserIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void findById() {
        User expected = new User();
        expected.setId(7);
        expected.setName("user7");
        expected.setEnabled(true);
        LocalDateTime dateTime = LocalDateTime.parse("2020-12-01T00:00:00");
        expected.setCreatedAt(dateTime);
        expected.setUpdatedAt(dateTime);

        User actual = restTemplate.getForObject("/users/{id}", User.class, 7);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}
