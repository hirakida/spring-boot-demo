package com.example;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> findById(long id) {
        return userRepository.findById(id);
    }

    public Mono<User> create(String name) {
        User user = new User();
        user.setName(name);
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public Mono<User> update(User user) {
        return userRepository.findById(user.getId())
                             .flatMap(entity -> {
                                 entity.setName(user.getName());
                                 return userRepository.save(entity);
                             });
    }

    public Mono<Void> deleteById(long id) {
        return userRepository.deleteById(id);
    }
}
