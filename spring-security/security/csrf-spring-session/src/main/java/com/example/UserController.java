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
        return "redirect:/";
    }

    @Data
    private static class UserForm {
        @NotEmpty
        private String lastName;
        @NotEmpty
        private String firstName;
    }
}
