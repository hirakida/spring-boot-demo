package com.example;

import org.reactivestreams.Publisher;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final UserRepository userRepository;

    @GetMapping("/users")
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Mono<User> findById(@PathVariable String id) {
        return userRepository.findById(id);
    }

    @GetMapping(path = "/users", params = "name")
    public Flux<User> findByName(@RequestParam String name) {
        return userRepository.findByName(name);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> create(@RequestBody Publisher<User> user) {
        return userRepository.saveAll(user).single();
    }

    @PutMapping("/users/{id}")
    public Mono<User> update(@PathVariable String id, @RequestBody User user) {
        return userRepository.findById(id)
                             .flatMap(document -> {
                                 BeanUtils.copyProperties(user, document, "createdAt");
                                 return userRepository.save(document);
                             });
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return userRepository.deleteById(id);
    }
}
