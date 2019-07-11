package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final UserMapper userMapper;

    @GetMapping("/users")
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @GetMapping(value = "/users", params = "ids")
    public List<User> findByIds(@RequestParam List<Long> ids) {
        return userMapper.findByIds(ids);
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable long id) {
        return userMapper.findById(id);
    }
}
