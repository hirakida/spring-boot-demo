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
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        // ログイン済みの場合はリダイレクトする
        if (userDetails != null) {
            return "redirect:/logged_in";
        }
        return "login_form";
    }

    @GetMapping("/logged_in")
    public String loggedIn(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        model.addAttribute("userDetails", userDetails);
        return "logged_in";
    }
}
