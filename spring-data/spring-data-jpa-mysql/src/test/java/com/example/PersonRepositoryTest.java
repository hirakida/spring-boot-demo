package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Testcontainers
class PersonRepositoryTest {
    @Container
    @ServiceConnection
    private static final MySQLContainer<?> CONTAINER =
            new MySQLContainer<>("mysql:8.0").withEnv("TZ", "Asia/Tokyo");
    @Autowired
    private PersonRepository repository;

    @Test
    void findAll() {
        List<Person> actual = repository.findAll();
        assertEquals(5, actual.size());
    }
}
