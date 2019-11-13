package com.example;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class ApiController {
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    public ApiController(RestTemplateBuilder builder, UserRepository userRepository) {
        restTemplate = builder.build();
        this.userRepository = userRepository;
    }

    @GetMapping("/datetime")
    public LocalDateTime getDateTime() {
        return restTemplate.getForObject("http://localhost:8081/datetime",
                                         LocalDateTime.class);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/github/users/{username}")
    public JsonNode getGitHubUser(@PathVariable String username) {
        return restTemplate.getForObject("https://api.github.com/users/{username}",
                                         JsonNode.class,
                                         username);
    }
}
