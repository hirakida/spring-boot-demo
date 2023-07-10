package com.example;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {
    private final PersonRepository repository;

    @GetMapping
    public Flux<Person> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Person> findById(@PathVariable int id) {
        return repository.findById(id);
    }

    @PostMapping
    public Mono<Person> create(@RequestBody Request request) {
        Person person = new Person();
        person.setName(request.getName());
        person.setCreatedAt(LocalDateTime.now());
        return repository.save(person);
    }

    @PutMapping("/{id}")
    public Mono<Person> update(@PathVariable int id, @RequestBody Request request) {
        return repository.findById(id)
                         .flatMap(person -> {
                             person.setName(request.getName());
                             return repository.save(person);
                         });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable int id) {
        return repository.deleteById(id);
    }

    @Data
    public static class Request {
        private String name;
    }
}
