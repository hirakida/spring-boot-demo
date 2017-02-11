package com.example;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class WebController {

    final AccountService accountService;

    @GetMapping("/")
    public String index(@AuthenticationPrincipal UserDetails userDetails,
                        Model model) {
        Account account = accountService.findAccount(userDetails.getUsername());
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("account", account);
        return "index";
    }

    @GetMapping("/expression")
    public String expression(
            @AuthenticationPrincipal(expression = "@accountService.findAccount(username)") Account account,
            Model model) {
        model.addAttribute("account", account);
        return "expression";
    }
}
