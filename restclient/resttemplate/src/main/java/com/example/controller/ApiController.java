package com.example.controller;

import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.GitHubApiClient;
import com.example.model.GitHubUser;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final GitHubApiClient gitHubApiClient;

    @GetMapping("/users/{userName}")
    public GitHubUser users(@PathVariable String userName) {
        return gitHubApiClient.getUsers(userName);
    }

    @GetMapping("/users/{userName}/resource")
    public Resource usersWithResource(@PathVariable String userName) {
        return gitHubApiClient.getUsersWithResource(userName);
    }

    @GetMapping("/options")
    public Set<HttpMethod> options() {
        return gitHubApiClient.options();
    }
}
