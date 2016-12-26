package com.example.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.GitHubApiClient;
import com.example.client.GitHubUser;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    GitHubApiClient gitHubApiClient;

    @GetMapping("/github/users/{userName}")
    public GitHubUser users(@PathVariable String userName) {
        return gitHubApiClient.getUsers(userName);
    }

    @GetMapping("/github/options")
    public Set<HttpMethod> options() {
        return gitHubApiClient.options();
    }
}
