package com.example.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.model.Follow;
import com.example.model.User;

@FeignClient(name = "github", url = "https://api.github.com")
public interface GitHubApiClient {

    @GetMapping("/users/{username}")
    User getUser(@PathVariable String username);

    @GetMapping("/users/{username}/following")
    List<Follow> getUserFollowing(@PathVariable String username);
}
