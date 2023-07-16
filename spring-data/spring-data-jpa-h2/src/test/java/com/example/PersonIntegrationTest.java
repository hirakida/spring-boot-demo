package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PersonIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void findById() {
        Person expected = new Person();
        expected.setId(1);
        expected.setName("person1");
        expected.setEnabled(true);
        LocalDateTime dateTime = LocalDateTime.of(2019, 1, 2, 12, 0);
        expected.setCreatedAt(dateTime);
        expected.setUpdatedAt(dateTime);

        Person actual = restTemplate.getForObject("/persons/{id}", Person.class, 1);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}
