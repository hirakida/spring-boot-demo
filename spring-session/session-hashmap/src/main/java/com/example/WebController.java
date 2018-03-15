package com.example;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WebController {

    private final AppSession appSession;

    @GetMapping("/")
    public String index(Model model) {
        log.info("{}", appSession);
        if (appSession.getUuid() == null) {
            appSession.setUuid(UUID.randomUUID().toString());
            appSession.setLocalDateTime(LocalDateTime.now());
        }
        model.addAttribute("appSession", appSession);
        return "index";
    }

    @GetMapping("/clear")
    public String clear(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
