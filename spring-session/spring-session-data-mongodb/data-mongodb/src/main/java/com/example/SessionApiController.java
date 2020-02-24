package com.example;

import org.springframework.session.data.mongo.MongoIndexedSessionRepository;
import org.springframework.session.data.mongo.MongoSession;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SessionApiController {
    private final MongoIndexedSessionRepository sessionRepository;

    @PostMapping("/sessions")
    public MongoSession create() {
        return sessionRepository.createSession();
    }

    @GetMapping("/sessions/{id}")
    public MongoSession findById(@PathVariable String id) {
        return sessionRepository.findById(id);
    }

    @DeleteMapping("/sessions/{id}")
    public void deleteById(@PathVariable String id) {
        sessionRepository.deleteById(id);
    }
}
