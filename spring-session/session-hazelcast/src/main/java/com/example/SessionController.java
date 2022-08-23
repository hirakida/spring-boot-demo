package com.example;

import javax.servlet.http.HttpSession;

import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SessionController {
    private final SessionRepository<?> sessionRepository;

    @GetMapping("/")
    public String index(HttpSession session) {
        return session.getId();
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
