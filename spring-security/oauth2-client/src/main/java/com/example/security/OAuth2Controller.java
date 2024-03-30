package com.example.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class OAuth2Controller {
    @GetMapping("/")
    public String index(@AuthenticationPrincipal OAuth2User oauth2User, Model model) {
        log.info("name: {}, authorities: {}", oauth2User.getName(), oauth2User.getAuthorities());
        model.addAttribute("userAttributes", oauth2User.getAttributes());
        return "index";
    }
}
