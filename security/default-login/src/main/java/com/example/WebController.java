package com.example;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebController {

    @GetMapping("/")
    public String index(
            @AuthenticationPrincipal(expression = "@accountService.findAccount(username)") Account account,
            Model model) {
        model.addAttribute("account", account);
        return "index";
    }
}
