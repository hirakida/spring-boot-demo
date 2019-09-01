package com.example;

import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final FindByIndexNameSessionRepository<?> sessionRepository;

    @PostMapping("/session")
    public Session createSession() {
        return sessionRepository.createSession();
    }

    @GetMapping("/session/{id}")
    public Session findById(@PathVariable String id) {
        return sessionRepository.findById(id);
    }

    @DeleteMapping("/session/{id}")
    public void deleteById(@PathVariable String id) {
        sessionRepository.deleteById(id);
    }
}
