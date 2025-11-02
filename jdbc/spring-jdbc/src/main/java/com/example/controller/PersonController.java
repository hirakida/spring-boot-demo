package com.example.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Person;
import com.example.repository.PersonRepository;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {
    private final PersonRepository personRepository;

    @GetMapping
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable int id) {
        return personRepository.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody @Validated PersonRequest request) {
        Person user = personRepository.findById(id).orElseThrow();
        user.setName(request.getName());
        personRepository.update(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        personRepository.deleteById(id);
    }

    @Data
    public static class PersonRequest {
        @NotNull
        private String name;
    }
}
