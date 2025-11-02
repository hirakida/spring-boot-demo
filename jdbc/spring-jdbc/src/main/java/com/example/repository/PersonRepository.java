package com.example.repository;

import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Person;

import lombok.RequiredArgsConstructor;

@Repository
@Transactional
@RequiredArgsConstructor
public class PersonRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT id, name, created_at, updated_at FROM person",
                                  new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> findById(int id) {
        Person person = jdbcTemplate.queryForObject(
                "SELECT id, name, created_at, updated_at FROM person WHERE id = :id",
                Map.of("id", id),
                new BeanPropertyRowMapper<>(Person.class));
        return Optional.ofNullable(person);
    }

    public Person update(Person person) {
        person.setUpdatedAt(LocalDateTime.now());
        jdbcTemplate.update("UPDATE person SET name = :name, updated_at = :updatedAt WHERE id = :id",
                            new BeanPropertySqlParameterSource(person));
        return person;
    }

    public int[] batchUpdate(List<Person> persons) {
        LocalDateTime now = LocalDateTime.now();
        List<Person> candidates = persons.stream()
                                         .peek(person -> person.setUpdatedAt(now))
                                         .collect(toList());

        return jdbcTemplate.batchUpdate(
                "UPDATE person SET name = :name, updated_at = :updatedAt WHERE id = :id",
                SqlParameterSourceUtils.createBatch(candidates));
    }

    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id = :id",
                            Map.of("id", id));
    }
}
