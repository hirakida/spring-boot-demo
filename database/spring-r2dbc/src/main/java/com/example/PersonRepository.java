package com.example;

import java.time.LocalDateTime;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Transactional
@RequiredArgsConstructor
public class PersonRepository {
    private final DatabaseClient client;

    public Flux<Person> findAll() {
        return client.sql("SELECT id, name, created_at FROM person")
                     .map(PersonRepository::toPerson)
                     .all();
    }

    public Mono<Person> findById(Integer id) {
        return client.sql("SELECT id, name, created_at FROM person WHERE id=:id")
                     .bind("id", id)
                     .map(PersonRepository::toPerson)
                     .one();
    }

    public Mono<Integer> insert(Person person) {
        return client.sql("INSERT INTO person(name, created_at) VALUES (:name, :created_at)")
                     .filter((statement, next) -> next.execute(statement.returnGeneratedValues("id")))
                     .bind("name", person.getName())
                     .bind("created_at", person.getCreatedAt())
                     .fetch()
                     .rowsUpdated();
    }

    public Mono<Integer> update(Person person) {
        return client.sql("UPDATE person SET name=:name WHERE id=:id")
                     .bind("id", person.getId())
                     .bind("name", person.getName())
                     .fetch()
                     .rowsUpdated();
    }

    public Mono<Integer> deleteById(Integer id) {
        return client.sql("DELETE FROM person WHERE id=:id")
                     .bind("id", id)
                     .fetch()
                     .rowsUpdated();
    }

    private static Person toPerson(Row row) {
        return new Person(row.get("id", Integer.class),
                          row.get("name", String.class),
                          row.get("created_at", LocalDateTime.class));
    }
}
