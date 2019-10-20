package com.example;

import java.time.LocalDateTime;

import org.springframework.session.data.mongo.MongoSession;
import org.springframework.session.data.mongo.ReactiveMongoSessionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {
    private final ReactiveMongoSessionRepository sessionRepository;

    @GetMapping("/")
    public Mono<WebSession> index(WebSession session) {
        log.info("sessionId={}", session.getId());
        if (session.getAttributes().get("key1") == null) {
            session.getAttributes().put("key1", LocalDateTime.now());
        }
        return Mono.just(session);
    }

    @GetMapping("/invalidate")
    public Mono<Void> invalidate(WebSession session) {
        log.info("invalidate: sessionId={}", session.getId());
        return session.invalidate();
    }

    @GetMapping("/sessions/{id}")
    public Mono<MongoSession> findById(@PathVariable String id) {
        return sessionRepository.findById(id);
    }
}
