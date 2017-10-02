package com.example.controller;

import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.GitHubApiClient;
import com.example.client.GitHubAsyncApiClient;
import com.example.client.GitHubUser;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ApiController {

    private final GitHubApiClient gitHubApiClient;
    private final GitHubAsyncApiClient gitHubAsyncApiClient;

    @GetMapping("/users/{userName}")
    public GitHubUser users(@PathVariable String userName) {
        return gitHubApiClient.getUsers(userName);
    }

    @GetMapping("/users/{userName}/resource")
    public Resource usersWithResource(@PathVariable String userName) {
        return gitHubApiClient.getUsersWithResource(userName);
    }

    @GetMapping("/users/{userName}/async")
    public GitHubUser usersWithAsync(@PathVariable String userName) {
        return gitHubAsyncApiClient.getUsers(userName);
    }

    @GetMapping("/options")
    public Set<HttpMethod> options() {
        return gitHubApiClient.options();
    }
}
