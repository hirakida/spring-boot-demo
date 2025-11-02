package com.example.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.repository.UserRepository;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable int id) {
        return userRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public User create(@RequestBody @Validated UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        return userRepository.insert(user);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody @Validated UserRequest request) {
        User user = userRepository.findById(id).orElseThrow();
        user.setName(request.getName());
        userRepository.update(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @Data
    public static class UserRequest {
        @NotNull
        private String name;
    }
}
