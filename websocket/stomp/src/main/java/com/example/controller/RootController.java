package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String index(Model model) {
        long id = 1;
        model.addAttribute("roomId", id);
        return "index";
    }
}
