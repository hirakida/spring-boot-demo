package com.example;

import static com.example.HelloController.HELLO;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@SessionAttributes(HELLO)
@RequiredArgsConstructor
@Slf4j
public class HelloController {
    static final String HELLO = "hello";
    private final SessionScopedBean sessionScopedBean;

    @GetMapping("/")
    public String index(HttpSession httpSession, Model model) {
        log.info("sessionId={}", httpSession.getId());
        if (!model.containsAttribute(HELLO)) {
            model.addAttribute(HELLO, new HelloSession(LocalDateTime.now()));
        }
        model.addAttribute("sessionScopedBean", sessionScopedBean);
        return "index";
    }

    @PostMapping("/complete")
    public String complete(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
