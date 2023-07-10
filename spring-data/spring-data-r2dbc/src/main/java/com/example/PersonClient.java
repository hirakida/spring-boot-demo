package com.example;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PersonClient {
    private static final String COLUMN_ID = "id";
    private final R2dbcEntityTemplate template;

    public Flux<Person> findAll() {
        return template.select(Person.class)
                       .all();
    }

    public Mono<Person> findById(Integer id) {
        return template.select(Person.class)
                       .matching(query(where(COLUMN_ID).is(id)))
                       .first();
    }

    public Mono<Person> insert(Person person) {
        return template.insert(person);
    }

    public Mono<Person> update(Person person) {
        return template.update(person);
    }

    public Mono<Long> deleteAll() {
        return template.delete(Person.class)
                       .all();
    }

    public Mono<Long> deleteById(Integer id) {
        return template.delete(Person.class)
                       .matching(query(where(COLUMN_ID).is(id)))
                       .all();
    }
}
