package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.GitHubApiClient;
import com.example.model.GitHubUser;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/github")
@RequiredArgsConstructor
public class GitHubController {
    private final GitHubApiClient gitHubApiClient;

    @GetMapping("/{username}")
    public GitHubUser getUser(@PathVariable String username) {
        return gitHubApiClient.getUser(username);
    }
}
