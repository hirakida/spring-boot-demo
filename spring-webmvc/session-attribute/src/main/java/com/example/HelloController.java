package com.example;

import static com.example.HelloController.DATETIME_ATTRIBUTE;
import static com.example.HelloController.SESSION_ID_ATTRIBUTE;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes({ DATETIME_ATTRIBUTE, SESSION_ID_ATTRIBUTE })
public class HelloController {
    static final String DATETIME_ATTRIBUTE = "datetime";
    static final String SESSION_ID_ATTRIBUTE = "sessionId";

    @GetMapping("/")
    public String index(HttpSession httpSession, Model model) {
        if (!model.containsAttribute(DATETIME_ATTRIBUTE)) {
            model.addAttribute(SESSION_ID_ATTRIBUTE, httpSession.getId());
            model.addAttribute(DATETIME_ATTRIBUTE, LocalDateTime.now());
        }
        return "index";
    }

    @PostMapping("/complete")
    public String complete(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
