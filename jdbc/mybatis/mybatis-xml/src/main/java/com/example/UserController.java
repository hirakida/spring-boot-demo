package com.example;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserMapper userMapper;

    @GetMapping
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @GetMapping(params = "ids")
    public List<User> findByIds(@RequestParam List<Long> ids) {
        return userMapper.findByIds(ids);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable long id) {
        return userMapper.findById(id);
    }
}
