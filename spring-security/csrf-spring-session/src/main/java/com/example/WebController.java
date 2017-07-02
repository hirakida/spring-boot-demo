package com.example;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebController {

    @GetMapping("/")
    public String index(Model model) {
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
        @NotEmpty
        private String lastName;
        @NotEmpty
        private String firstName;
    }
}
