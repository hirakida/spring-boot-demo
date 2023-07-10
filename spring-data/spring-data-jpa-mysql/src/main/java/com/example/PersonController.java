package com.example;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonRepository personRepository;

    @GetMapping("/persons")
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @GetMapping("/persons/{id}")
    public Person findById(@PathVariable int id) {
        return personRepository.findById(id)
                               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
