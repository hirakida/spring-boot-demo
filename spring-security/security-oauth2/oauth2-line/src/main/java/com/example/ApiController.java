package com.example;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ApiController {

    @GetMapping("/")
    public OAuth2Authentication index(@AuthenticationPrincipal OAuth2Authentication authentication) {
        return authentication;
    }
}
