package com.example.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final UserRepository userRepository;

    @GetMapping("/users")
    public Page<User> findAll(@PageableDefault Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @GetMapping("/users/{name}")
    public List<User> findByName(@PathVariable String name) {
        return userRepository.findByName(name);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestParam String name) {
        return userRepository.save(User.of(name, LocalDateTime.now()));
    }
}
