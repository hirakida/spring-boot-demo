package com.example;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("male", Gender.MALE);
        model.addAttribute("female", Gender.FEMALE);
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "index";
    }
}
