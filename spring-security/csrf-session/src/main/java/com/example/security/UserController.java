package com.example.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "index";
    }

    @PostMapping("/")
    public String post(@Validated UserForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("userForm", form);
            return "index";
        }
        log.info("{}", form);
        return "redirect:/";
    }

    @Data
    public static class UserForm {
        @NotEmpty
        private String lastName;
        @NotEmpty
        private String firstName;
    }
}
