package com.example;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RootController {
    private final ConnectionRepository connectionRepository;

    @GetMapping
    public String index() {
        Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
        if (connection == null) {
            return "redirect:/connect/facebook";
        }
        return "index";
    }
}
