package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.GitHubApiClient;
import com.example.client.GitHubApiClient.GitHubUser;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ApiController {

    final GitHubApiClient gitHubApiClient;

    @GetMapping("/github/users/{userName}")
    public GitHubUser users(@PathVariable String userName) {
        return gitHubApiClient.getUsers(userName);
    }

    @GetMapping("/github/users/{userName}/async")
    public GitHubUser usersWithAsync(@PathVariable String userName) {
        return gitHubApiClient.getUsers(userName);
    }
}
