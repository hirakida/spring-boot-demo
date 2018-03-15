package com.example;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.core.Account;
import com.example.core.AuthPrincipalAccount;
import com.example.core.UserDetailsImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            return "redirect:/logged_in";
        }
        return "login";
    }

    // UserDetailsService
    @GetMapping("/logged_in")
    public String loggedIn(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        model.addAttribute("userDetails", userDetails);
        return "logged_in";
    }

    @GetMapping("/logged_in/2")
    public String loggedIn2(
            @AuthenticationPrincipal(expression = "@accountService.findOne(username)") Account account,
            Model model) {
        model.addAttribute("account", account);
        return "logged_in";
    }

    @GetMapping("/logged_in/3")
    public String loggedIn3(
            @AuthenticationPrincipal(expression = "@accountService.findOne(#this)") Account account,
            Model model) {
        model.addAttribute("account", account);
        return "logged_in";
    }

    @GetMapping("/logged_in/4")
    public String loggedIn4(@AuthPrincipalAccount Account account, Model model) {
        model.addAttribute("account", account);
        return "logged_in";
    }
}
