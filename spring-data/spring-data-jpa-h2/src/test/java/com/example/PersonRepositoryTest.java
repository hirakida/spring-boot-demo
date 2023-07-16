package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;
import org.springframework.test.jdbc.JdbcTestUtils;

@DataJpaTest
@TestConstructor(autowireMode = AutowireMode.ALL)
class PersonRepositoryTest {
    @Autowired
    private PersonRepository repository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void findAll() {
        List<Person> result = repository.findAll();
        assertEquals(6, result.size());
    }

    @Test
    void save() {
        Person person = new Person();
        person.setName("person7");
        repository.save(person);

        List<Person> result = repository.findAll();
        int count = JdbcTestUtils.countRowsInTable(jdbcTemplate, "person");
        assertEquals(7, result.size());
        assertEquals(7, count);
    }

    @Test
    void findByUsername() {
        List<Person> result = repository.findByUsername("person1");
        assertEquals(1, result.size());
        assertEquals("person1", result.get(0).getName());
    }

    @Test
    void queryMethods() {
        List<Person> result = repository.findByName("person1");
        assertEquals(1, result.size());
        assertEquals("person1", result.get(0).getName());

        result = repository.findByNameLike("person%");
        assertEquals(6, result.size());

        result = repository.findByNameStartingWith("person");
        assertEquals(6, result.size());

        result = repository.findByNameEndingWith("2");
        assertEquals(1, result.size());
        assertEquals("person2", result.get(0).getName());

        result = repository.findByNameContaining("person");
        assertEquals(6, result.size());

        result = repository.findByIdLessThan(4);
        assertEquals(3, result.size());

        result = repository.findByIdGreaterThan(4);
        assertEquals(2, result.size());

        result = repository.findByEnabledTrue();
        assertEquals(6, result.size());

        result = repository.findByEnabledFalse();
        assertEquals(0, result.size());
    }
}
