package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.Data;

@Controller
@SessionAttributes("account")
public class WebController {

    @GetMapping("/")
    public String index(Model model) {
        Account account = new Account();
        account.setId(1L);
        account.setName("name1");
        account.setEmail("name1@example.com");
        model.addAttribute("account", account);
        return "index";
    }

    @PostMapping("/")
    public String create(@ModelAttribute Account account) {
        return "redirect:/complete";
    }

    @GetMapping("/complete")
    public String complete(SessionStatus sessionStatus, Model model) {
        if (!model.containsAttribute("account")) {
            return "redirect:/";
        }
        sessionStatus.setComplete();
        return "complete";
    }

    @Data
    public static class Account {
        private long id;
        private String name;
        private String email;
    }
}
