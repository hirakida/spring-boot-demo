package com.example;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService2 {
    private final UserRepository2 userRepository;
    private final TransactionalOperator transactionalOperator;

    public Mono<Void> insert(String name) {
        return transactionalOperator.transactional(
                userRepository.insert(new User(null, name, LocalDateTime.now()))
                              .doOnNext(user -> log.info("{}", user)));
    }

    public Flux<Void> insert(String... names) {
        return transactionalOperator.transactional(Flux.fromArray(names)
                                                       .map(name -> new User(null, name, LocalDateTime.now()))
                                                       .flatMap(userRepository::insert)
                                                       .doOnNext(user -> log.info("{}", user)));
    }
}
