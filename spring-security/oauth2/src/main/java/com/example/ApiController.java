package com.example;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ApiController {

    @GetMapping("/")
    public String index() {
        return "permit all";
    }

    @GetMapping("/logged_in")
    public OAuth2Authentication loggedIn(@AuthenticationPrincipal OAuth2Authentication authentication) {
        return authentication;
    }
}
