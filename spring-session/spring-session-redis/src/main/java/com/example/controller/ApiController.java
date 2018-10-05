package com.example.controller;

import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/session")
@RequiredArgsConstructor
public class ApiController {

    private final FindByIndexNameSessionRepository<?> findByIndexNameSessionRepository;

    @PostMapping
    public Session createSession() {
        return findByIndexNameSessionRepository.createSession();
    }

    @GetMapping("/{id}")
    public Session findById(@PathVariable String id) {
        return findByIndexNameSessionRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        findByIndexNameSessionRepository.deleteById(id);
    }
}
