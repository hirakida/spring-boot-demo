package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.elasticsearch.DataElasticsearchTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataElasticsearchTest
@Import(UserClient.class)
@Testcontainers
class UserClientTest {
    @Container
    @ServiceConnection
    private static final ElasticsearchContainer CONTAINER =
            new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch:7.17.10");
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
        List<SearchHit<User>> actual = client.search("test1");
        assertEquals(1, actual.size());
    }
}
