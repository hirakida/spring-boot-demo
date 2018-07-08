package com.example;

import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final GitHubApiClient gitHubApiClient;

    @GetMapping("/users/{userName}")
    public GitHubUser users(@PathVariable String userName) {
        ListenableFuture<ResponseEntity<GitHubUser>> future = gitHubApiClient.getUsers(userName);
        try {
            return future.get().getBody();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
