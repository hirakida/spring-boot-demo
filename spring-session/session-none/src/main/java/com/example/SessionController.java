package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
public class SessionController {
    @GetMapping("/")
    public String index(HttpSession session) {
        return session.getId();
    }

    @GetMapping("/invalidate")
    public void invalidate(HttpSession session) {
        session.invalidate();
    }
}
