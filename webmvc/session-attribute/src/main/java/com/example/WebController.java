package com.example;

import static com.example.WebController.ATTRIBUTE_NAME;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes(ATTRIBUTE_NAME)
public class WebController {
    static final String ATTRIBUTE_NAME = "datetime";
    private static final Logger log = LoggerFactory.getLogger("com.example.WebController");

    @GetMapping("/")
    public String index(HttpSession httpSession, Model model) {
        if (!model.containsAttribute(ATTRIBUTE_NAME)) {
            model.addAttribute(ATTRIBUTE_NAME, LocalDateTime.now());
        }
        log.info("{}", httpSession.getId());
        return "index";
    }

    @PostMapping("/complete")
    public String complete(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
