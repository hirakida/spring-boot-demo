package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AppController {

    @GetMapping("/")
    public String index() {
        return "redirect:/actuator";
    }
}
