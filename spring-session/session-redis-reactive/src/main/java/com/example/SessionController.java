package com.example;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

import reactor.core.publisher.Mono;

@RestController
public class SessionController {
    @GetMapping("/")
    public Mono<WebSession> index(WebSession session) {
        if (session.getAttributes().get("datetime") == null) {
            session.getAttributes().put("datetime", LocalDateTime.now());
        }
        return Mono.just(session);
    }

    @PostMapping("/invalidate")
    public Mono<Void> invalidate(WebSession session) {
        return session.invalidate();
    }
}
