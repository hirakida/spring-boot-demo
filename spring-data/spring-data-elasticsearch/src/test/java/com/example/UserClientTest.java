package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Testcontainers
class UserClientTest {
    @Container
    @ServiceConnection
    private static final ElasticsearchContainer CONTAINER =
            new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch:8.8.2");
    @Autowired
    private UserClient client;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setName("test1");
        client.index(user);
        client.refresh();
    }

    @Test
    void search() {
        List<SearchHit<User>> response = client.search("test1");
        assertEquals(1, response.size());
    }
}
