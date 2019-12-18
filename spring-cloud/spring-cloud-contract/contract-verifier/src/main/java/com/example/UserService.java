package com.example;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class UserService {

    public Mono<User> getUser(long id) {
        return Mono.just(new User(id, "name" + id));
    }
}
