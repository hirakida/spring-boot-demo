package com.example;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
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
    public String login(@AuthenticationPrincipal UserDetailsImpl user) {
        return user != null
               ? "redirect:/logged_in"
               : "login_form";
    }

    @GetMapping("/logged_in")
    public String loggedIn(@AuthenticationPrincipal UserDetailsImpl user) {
        log.info("username:{}", user != null ? user.getUsername() : StringUtils.EMPTY);
        return "logged_in";
    }
}
