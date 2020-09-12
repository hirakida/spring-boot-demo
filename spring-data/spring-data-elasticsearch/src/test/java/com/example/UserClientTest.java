package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Testcontainers
public class UserClientTest {
    @Container
    private static final ElasticsearchContainer CONTAINER =
            new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch-oss:7.6.2")
                    .withExposedPorts(9200);
    @Autowired
    private UserClient client;

    @DynamicPropertySource
    static void elasticsearchProperties(DynamicPropertyRegistry registry) {
        registry.add("elasticsearch.port", () -> CONTAINER.getMappedPort(9200));
    }

    @BeforeEach
    public void init() {
        User user = new User();
        user.setName("test1");
        client.index(user);
        client.refresh();
    }

    @Test
    public void search() {
        List<SearchHit<User>> response = client.search("test1");
        assertEquals(1, response.size());
    }
}
