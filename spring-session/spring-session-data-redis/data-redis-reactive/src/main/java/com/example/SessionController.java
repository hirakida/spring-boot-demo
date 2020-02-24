package com.example;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class SessionController {

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
}
