package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final UserRepository userRepository;

    @GetMapping("/users/{id}")
    public User get(@PathVariable long id) {
        return userRepository.get(id)
                             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable long id) {
        userRepository.delete(id);
    }
}
