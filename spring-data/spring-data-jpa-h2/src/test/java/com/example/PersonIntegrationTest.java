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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = AutowireMode.ALL)
class PersonIntegrationTest {
    private final TestRestTemplate restTemplate;

    PersonIntegrationTest(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Test
    void findById() {
        Person person = new Person();
        person.setId(1);
        person.setName("person1");
        person.setEnabled(true);
        LocalDateTime dateTime = LocalDateTime.of(2019, 1, 2, 12, 0);
        person.setCreatedAt(dateTime);
        person.setUpdatedAt(dateTime);

        Person response = restTemplate.getForObject("/persons/{id}", Person.class, 1);
        assertNotNull(response);
        assertEquals(person, response);
    }
}
