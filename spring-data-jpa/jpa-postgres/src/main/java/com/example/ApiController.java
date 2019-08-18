package com.example;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
        return personRepository.findById(id)
                               .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }
}
