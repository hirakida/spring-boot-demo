package com.example;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
                     .mapProperties(Person.class)
                     .all();
    }

    public Mono<Person> findById(Integer id) {
        return client.sql("SELECT id, name, created_at FROM person WHERE id = :id")
                     .bind("id", id)
                     .mapProperties(Person.class)
                     .one();
    }

    public Mono<Long> insert(Person person) {
        return client.sql("INSERT INTO person(name, created_at) VALUES (:name, :created_at)")
                     .filter((statement, next) -> next.execute(statement.returnGeneratedValues("id")))
                     .bind("name", person.getName())
                     .bind("created_at", person.getCreatedAt())
                     .fetch()
                     .rowsUpdated();
    }

    public Mono<Long> update(Person person) {
        return client.sql("UPDATE person SET name=:name WHERE id=:id")
                     .bind("id", person.getId())
                     .bind("name", person.getName())
                     .fetch()
                     .rowsUpdated();
    }

    public Mono<Long> deleteById(Integer id) {
        return client.sql("DELETE FROM person WHERE id=:id")
                     .bind("id", id)
                     .fetch()
                     .rowsUpdated();
    }
}
