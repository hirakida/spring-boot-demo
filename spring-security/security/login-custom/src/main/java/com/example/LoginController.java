package com.example;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.model.Account;
import com.example.userdetails.AuthenticationPrincipalAccount;
import com.example.userdetails.UserDetailsImpl;

@Controller
public class LoginController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            return "redirect:/success";
        }
        return "login";
    }

    // UserDetailsService
    @GetMapping("/success")
    public String success(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        model.addAttribute("userDetails", userDetails);
        return "logged_in";
    }

    // expression: findOne(username)
    @GetMapping("/success2")
    public String success2(
            @AuthenticationPrincipal(expression = "@accountService.findOne(username)") Account account,
            Model model) {
        model.addAttribute("account", account);
        return "logged_in";
    }

    // expression: fineOne(userDetails)
    @GetMapping("/success3")
    public String success3(
            @AuthenticationPrincipal(expression = "@accountService.findOne(#this)") Account account,
            Model model) {
        model.addAttribute("account", account);
        return "logged_in";
    }

    // annotation + expression
    @GetMapping("/success4")
    public String success4(@AuthenticationPrincipalAccount Account account, Model model) {
        model.addAttribute("account", account);
        return "logged_in";
    }
}
