package com.example;

import java.time.LocalDateTime;

import org.springframework.session.Session;
import org.springframework.session.data.mongo.MongoIndexedSessionRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SessionController {
    private final MongoIndexedSessionRepository sessionRepository;

    @GetMapping("/")
    public String index(HttpSession session) {
        if (session.getAttribute("datetime") == null) {
            session.setAttribute("datetime", LocalDateTime.now());
        }
        return session.getId();
    }

    @DeleteMapping("/")
    public void invalidate(HttpSession session) {
        session.invalidate();
    }

    @PostMapping("/")
    public Session create() {
        return sessionRepository.createSession();
    }

    @GetMapping("/{id}")
    public Session findById(@PathVariable String id) {
        return sessionRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        sessionRepository.deleteById(id);
    }
}
