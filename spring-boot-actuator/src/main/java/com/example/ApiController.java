package com.example;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.GitHubClient;
import com.example.client.User;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final GitHubClient gitHubClient;

    @GetMapping("/now")
    @Timed("now")
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    @GetMapping("/github/{username}")
    @Timed("github")
    public User getUser(@PathVariable String username) {
        return gitHubClient.getUser(username);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFound() {
    }
}
