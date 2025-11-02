package com.example;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    void findAll() {
        List<User> result = repository.findAll();
        assertEquals(5, result.size());

        // SelectType.STREAM
        result = repository.findAll(stream -> stream.collect(toList()));
        assertEquals(5, result.size());

        // SelectType.COLLECT
        result = repository.findAll(toList());
        assertEquals(5, result.size());
    }

    @Test
    void findAll_Map() {
        // SelectType.COLLECT
        final Map<Long, List<User>> result = repository.findAll(groupingBy(User::getId));
        assertEquals(5, result.size());
        result.forEach((id, users) -> assertEquals(1, users.size()));
    }
}
