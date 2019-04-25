package com.example;

import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.Data;

@Controller
public class WebController {

    @GetMapping("/")
    public String get(Model model) {
        model.addAttribute("form", new Form());
        return "index";
    }

    @PostMapping("/")
    public String post(@Validated Form form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("form", form);
            return "index";
        }
        return "redirect:/";
    }

    @Data
    private static class Form {
        private @NotEmpty String lastName;
        private @NotEmpty String firstName;
    }
}
