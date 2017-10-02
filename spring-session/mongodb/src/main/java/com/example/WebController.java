package com.example;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class WebController {

    private final AppSessionAttr sessionAttr;

    @GetMapping("/")
    public String index(Model model) {
        setSessionAttr();
        model.addAttribute("sessionAttr", sessionAttr);
        return "index";
    }

    private void setSessionAttr() {
        log.info("{}", sessionAttr);
        if (sessionAttr.getUuid() == null) {
            sessionAttr.setUuid(UUID.randomUUID().toString());
            sessionAttr.setLocalDateTime(LocalDateTime.now());
        }
    }
}
