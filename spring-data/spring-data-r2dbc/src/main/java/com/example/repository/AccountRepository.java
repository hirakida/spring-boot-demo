package com.example.repository;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;

import com.example.model.Account;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AccountRepository {
    private static final String COLUMN_ID = "id";
    private final R2dbcEntityTemplate template;

    public Flux<Account> findAll() {
        return template.select(Account.class)
                       .all();
    }

    public Mono<Account> findById(Integer id) {
        return template.select(Account.class)
                       .matching(query(where(COLUMN_ID).is(id)))
                       .first();
    }

    public Mono<Account> insert(Account account) {
        return template.insert(account);
    }

    public Mono<Account> update(Account account) {
        return template.update(account);
    }

    public Mono<Integer> deleteAll() {
        return template.delete(Account.class)
                       .all();
    }

    public Mono<Integer> deleteById(Integer id) {
        return template.delete(Account.class)
                       .matching(query(where(COLUMN_ID).is(id)))
                       .all();
    }
}
