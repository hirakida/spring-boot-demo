package com.example;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
