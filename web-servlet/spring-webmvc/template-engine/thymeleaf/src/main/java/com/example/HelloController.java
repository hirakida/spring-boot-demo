package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String index(Model model) {
        String text = "<b>text</b>";
        model.addAttribute("text", text);
        model.addAttribute("data1", "hello");
        model.addAttribute("data2", "thymeleaf");
        return "index";
    }

    public String getMessage() {
        return "instance method";
    }

    public static String getStaticMessage() {
        return "static method";
    }
}
