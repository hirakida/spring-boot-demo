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

    @GetMapping("/accounts")
    public String get(Model model) {
        model.addAttribute("accountForm", new AccountForm());
        return "account";
    }

    @PostMapping("/accounts")
    public String post(@Validated AccountForm accountForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("accountForm", accountForm);
            return "account";
        }
        return "redirect:accounts";
    }

    @Data
    private static class AccountForm {
        @NotEmpty
        private String lastName;
        @NotEmpty
        private String firstName;
    }
}
