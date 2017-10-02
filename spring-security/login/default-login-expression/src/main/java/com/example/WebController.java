package com.example;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index(
            @AuthenticationPrincipal(expression = "@accountService.findAccount(username)") Account account,
            Model model) {
        model.addAttribute("account", account);
        return "index";
    }

    @GetMapping("/2")
    public String index2(
            @AuthenticationPrincipal(expression = "@accountService.findAccount(#this)") Account account,
            Model model) {
        model.addAttribute("account", account);
        return "index";
    }

    @GetMapping("/3")
    public String index3(@AuthPrincipalAccount Account account, Model model) {
        model.addAttribute("account", account);
        return "index";
    }
}
