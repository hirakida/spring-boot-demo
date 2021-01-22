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
public class UserClient {
    private static final String COLUMN_ID = "id";
    private final R2dbcEntityTemplate template;

    public Flux<User> findAll() {
        return template.select(User.class)
                       .all();
    }

    public Mono<User> findById(Integer id) {
        return template.select(User.class)
                       .matching(query(where(COLUMN_ID).is(id)))
                       .first();
    }

    public Mono<User> insert(User account) {
        return template.insert(account);
    }

    public Mono<User> update(User account) {
        return template.update(account);
    }

    public Mono<Integer> deleteAll() {
        return template.delete(User.class)
                       .all();
    }

    public Mono<Integer> deleteById(Integer id) {
        return template.delete(User.class)
                       .matching(query(where(COLUMN_ID).is(id)))
                       .all();
    }
}
