package com.example;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonRepository repository;

    @GetMapping("/persons")
    public Page<Person> findAll(@PageableDefault Pageable pageable) {
        return repository.findAll(pageable);
    }

    @GetMapping("/persons/{id}")
    public Person findById(@PathVariable("id") Person person) {
        return person;
    }

    @PostMapping("/persons")
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody @Validated Request request) {
        Person person = new Person();
        person.setName(request.getName());
        return repository.save(person);
    }

    @PutMapping("/persons/{id}")
    public Person update(@PathVariable int id, @RequestBody @Validated Request request) {
        Person person = repository.findById(id)
                                  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        person.setName(request.getName());
        return repository.save(person);
    }

    @DeleteMapping("/persons/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        Person person = repository.findById(id)
                                  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repository.delete(person);
    }

    @Data
    public static class Request {
        @NotNull
        private String name;
    }
}
