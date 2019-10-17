package com.example.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.SessionScopedBean;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AppController {
    private final SessionScopedBean sessionScopedBean;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        log.info("{}", sessionScopedBean);
        if (sessionScopedBean.getId() == null) {
            sessionScopedBean.setId(session.getId());
            sessionScopedBean.setLocalDateTime(LocalDateTime.now());
        }
        model.addAttribute("sessionScopedBean", sessionScopedBean);
        return "index";
    }

    @PostMapping("/")
    public String invalidate(HttpSession session) {
        log.info("invalidate: {}", sessionScopedBean);
        session.invalidate();
        log.info("invalidate: {}", sessionScopedBean);
        return "redirect:/";
    }
}
