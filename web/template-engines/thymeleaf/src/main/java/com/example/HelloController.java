package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/{statusCode}")
    public String error(@PathVariable int statusCode) {
        throw new ResponseStatusException(HttpStatus.valueOf(statusCode));
    }

    public String getMessage() {
        return "instance method";
    }

    public static String getStaticMessage() {
        return "static method";
    }
}
