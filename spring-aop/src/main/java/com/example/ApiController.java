package com.example;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {
    private final UserService userService;

    @GetMapping("/users")
    public List<User> findAll() {
        log.info("findAll");
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable long id) {
        log.info("findById id={}", id);
        return userService.findById(id);
    }

    @PostMapping("/users")
    public User create(@RequestBody UserRequest request) {
        log.info("create {}", request);
        return userService.create(request.getName());
    }

    @PutMapping("/users/{id}")
    public User update(@PathVariable long id, @RequestBody UserRequest request) {
        log.info("update id={} name={}", id, request.getName());
        return userService.update(new User(id, request.getName()));
    }

    @DeleteMapping("/users/{id}")
    public void deleteById(@PathVariable long id) {
        log.info("deleteById id={}", id);
        userService.deleteById(id);
    }

    @Data
    public static class UserRequest {
        private String name;
    }
}
