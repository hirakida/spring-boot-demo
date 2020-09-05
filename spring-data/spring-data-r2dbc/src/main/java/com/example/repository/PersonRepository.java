package com.example.repository;

import static org.springframework.data.relational.core.query.Criteria.where;

import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

import com.example.model.Person;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PersonRepository {
    private static final String COLUMN_ID = "id";
    private final DatabaseClient client;

    public Flux<Person> findAll() {
        return client.select()
                     .from(Person.class)
                     .fetch()
                     .all();
    }

    public Flux<Person> findAll2() {
        return client.execute("SELECT `id`, `name`, `created_at` FROM `person`")
                     .as(Person.class)
                     .fetch()
                     .all();
    }

    public Mono<Person> findById(Integer id) {
        return client.select()
                     .from(Person.class)
                     .matching(where(COLUMN_ID).is(id))
                     .fetch()
                     .one();
    }

    public Mono<Void> insert(Person person) {
        return client.insert()
                     .into(Person.class)
                     .using(person)
                     .then();
    }

    public Mono<Void> update(Person person) {
        return client.update()
                     .table(Person.class)
                     .using(person)
                     .then();
    }

    public Mono<Void> deleteAll() {
        return client.delete()
                     .from(Person.class)
                     .then();
    }

    public Mono<Void> deleteById(Integer id) {
        return client.delete()
                     .from(Person.class)
                     .matching(where(COLUMN_ID).is(id))
                     .then();
    }
}
