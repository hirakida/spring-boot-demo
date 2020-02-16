package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.service.GitHubService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GitHubController {
    private final GitHubService gitHubService;

    @GetMapping("/users/{username}")
    public User getUser(@PathVariable String username) {
        return gitHubService.getUser(username);
    }
}
