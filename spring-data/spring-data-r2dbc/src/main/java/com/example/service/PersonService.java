package com.example.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;

import com.example.model.Person;
import com.example.repository.PersonRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {
    private final PersonRepository personRepository;
    private final TransactionalOperator transactionalOperator;

    public Flux<Person> findAll() {
        return personRepository.findAll();
    }

    public Mono<Person> findById(int id) {
        return personRepository.findById(id);
    }

    public Mono<Integer> insert(Person person) {
        return transactionalOperator.transactional(personRepository.insert(person));
    }

    public Flux<Integer> insert(String... names) {
        return transactionalOperator.transactional(Flux.fromArray(names)
                                                       .map(name -> new Person(null, name, LocalDateTime.now()))
                                                       .flatMap(personRepository::insert)
                                                       .doOnNext(low -> log.info("{}", low)));
    }
}
