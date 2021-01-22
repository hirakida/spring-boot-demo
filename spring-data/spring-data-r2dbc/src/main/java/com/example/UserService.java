package com.example;

import java.time.LocalDateTime;
import java.util.List;

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

    public Mono<User> findById(int id) {
        return userRepository.findById(id);
    }

    public Mono<Void> deleteById(int id) {
        return userRepository.deleteById(id);
    }

    public Flux<User> saveAll(List<User> users) {
        return userRepository.saveAll(users);
    }

    public Mono<User> create(User user) {
        return Mono.fromSupplier(() -> {
            user.setCreatedAt(LocalDateTime.now());
            return user;
        }).flatMap(userRepository::save);
    }

    public Mono<User> update(User user) {
        return userRepository.findById(user.getId())
                             .flatMap(entity -> {
                                 entity.setName(user.getName());
                                 return userRepository.save(entity);
                             });
    }
}
