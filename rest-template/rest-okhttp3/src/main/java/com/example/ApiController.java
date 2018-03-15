package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
