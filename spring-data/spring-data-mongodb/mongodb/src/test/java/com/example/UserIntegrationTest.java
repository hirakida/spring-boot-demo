package com.example;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
@ContextConfiguration(initializers = MongoContextInitializer.class)
public class UserIntegrationTest {
    @Container
    private static final GenericContainer<?> CONTAINER = MongoContextInitializer.CONTAINER;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        mongoTemplate.dropCollection(User.class);
        List<User> users = IntStream.rangeClosed(1, 5)
                                    .mapToObj(i -> new User("name" + i))
                                    .collect(toList());
        mongoTemplate.insert(users, User.class);
    }

    @Test
    public void findById() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("name1"));
        User expected = mongoTemplate.findOne(query, User.class);

        User response = restTemplate.getForObject("/users/{id}", User.class, expected.getId());
        assertNotNull(response);
        assertEquals(expected, response);
    }
}
