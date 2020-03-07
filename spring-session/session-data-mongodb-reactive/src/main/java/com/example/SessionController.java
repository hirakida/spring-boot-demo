package com.example;

import java.time.LocalDateTime;

import org.springframework.session.data.mongo.MongoSession;
import org.springframework.session.data.mongo.ReactiveMongoSessionRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class SessionController {
    private final ReactiveMongoSessionRepository sessionRepository;

    @GetMapping("/")
    public Mono<WebSession> index(WebSession session) {
        if (session.getAttributes().get("datetime") == null) {
            session.getAttributes().put("datetime", LocalDateTime.now());
        }
        return Mono.just(session);
    }

    @DeleteMapping("/")
    public Mono<Void> invalidate(WebSession session) {
        return session.invalidate();
    }

    @GetMapping("/{id}")
    public Mono<MongoSession> findById(@PathVariable String id) {
        return sessionRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable String id) {
        return sessionRepository.deleteById(id);
    }
}
