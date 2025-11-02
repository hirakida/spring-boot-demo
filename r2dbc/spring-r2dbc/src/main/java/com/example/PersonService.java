package com.example;

import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final TransactionalOperator transactionalOperator;

    public Flux<Person> findAll() {
        return personRepository.findAll();
    }

    public Mono<Person> findById(int id) {
        return personRepository.findById(id);
    }

    public Mono<Long> create(Person person) {
        return personRepository.insert(person);
    }

    public Mono<Long> update(Person person) {
        return transactionalOperator.transactional(
                personRepository.findById(person.getId())
                                .flatMap(model -> {
                                    model.setName(person.getName());
                                    return personRepository.update(model);
                                }));
    }
}
