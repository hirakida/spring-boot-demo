package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.elasticsearch.DataElasticsearchTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataElasticsearchTest
@Testcontainers
class UserRepositoryTest {
    @Container
    @ServiceConnection
    private static final ElasticsearchContainer CONTAINER =
            new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch:7.17.10");
    @Autowired
    private UserRepository repository;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setName("test1");
        repository.save(user);
    }

    @Test
    void findByNameLike() {
        List<User> actual = repository.findByNameLike("test1");
        assertEquals(1, actual.size());
    }
}
