package com.example;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final PersonRepository personRepository;

    @GetMapping("/persons")
    public Page<Person> findAll(@PageableDefault Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    @GetMapping("/persons/{id}")
    public Person findById(@PathVariable int id) {
        return personRepository.findById(id).orElseThrow();
    }
}
