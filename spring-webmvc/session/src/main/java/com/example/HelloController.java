package com.example;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@SessionAttributes(HelloController.HELLO_ATTRIBUTE)
@RequiredArgsConstructor
public class HelloController {
    static final String HELLO_ATTRIBUTE = "hello";
    private final SessionScopedBean sessionScopedBean;

    @GetMapping("/")
    public String index(HttpSession httpSession, Model model) {
        if (!model.containsAttribute(HELLO_ATTRIBUTE)) {
            model.addAttribute(HELLO_ATTRIBUTE, new HelloSessionAttribute(LocalDateTime.now()));
        }
        model.addAttribute("sessionId", httpSession.getId());
        model.addAttribute("sessionScopedBean", sessionScopedBean);
        return "index";
    }

    @PostMapping("/complete")
    public String complete(HttpSession httpSession, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        httpSession.invalidate();
        return "redirect:/";
    }

    public record HelloSessionAttribute(LocalDateTime datetime) {}
}
