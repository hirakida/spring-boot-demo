package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WebController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("message", "index");
        return "index";
    }

    @GetMapping("/2")
    public String filter(Model model) {
        model.addAttribute("message", "filter");
        return "index";
    }
}
