package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @GetMapping("/")
    public String index(Model model) {
        String text = "<b>text</b>";
        model.addAttribute("text", text);
        model.addAttribute("data1", "hello");
        model.addAttribute("data2", "thymeleaf");
        return "index";
    }

    @GetMapping(path = "/params")
    public String params(@RequestParam String param1, @RequestParam String param2, Model model) {
        String text = param1 + ':' + param2;
        model.addAttribute("text", text);
        return "index";
    }
}
