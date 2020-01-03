package com.example;

import static org.springframework.data.r2dbc.query.Criteria.where;

import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserRepository2 {
    private final DatabaseClient client;

    public Flux<User> findAll() {
        return client.select().from(User.class).fetch().all();
    }

    public Mono<User> findById(Integer id) {
        return client.select().from(User.class).matching(where("id").is(id)).fetch().one();
    }

    public Mono<Void> insert(User user) {
        return client.insert().into(User.class).using(user).then();
    }

    public Mono<Void> update(User user) {
        return client.update().table(User.class).using(user).then();
    }

    public Mono<Void> deleteAll() {
        return client.delete().from(User.class).then();
    }

    public Mono<Void> deleteById(Integer id) {
        return client.delete().from(User.class).matching(where("id").is(id)).then();
    }
}
