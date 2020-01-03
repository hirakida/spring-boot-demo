package com.example;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> findById(int id) {
        return userRepository.findById(id);
    }

    public Mono<Void> deleteById(int id) {
        return userRepository.deleteById(id);
    }

    public Flux<User> saveAll(List<User> users) {
        return userRepository.saveAll(users)
                             .doOnNext(user -> log.info("{}", user));
    }

    public Mono<User> insert(String name) {
        return Mono.just(new User(null, name, LocalDateTime.now()))
                   .flatMap(userRepository::save)
                   .doOnNext(user -> log.info("{}", user));
    }

    public Mono<User> update(User user) {
        return userRepository.findById(user.getId())
                             .map(entity -> {
                                 entity.setName(user.getName());
                                 return entity;
                             })
                             .flatMap(userRepository::save);
    }
}
