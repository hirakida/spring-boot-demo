package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public Flux<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<User> findById(@PathVariable int id) {
        return userService.findById(id);
    }

    @PostMapping
    public Mono<User> create(@RequestBody UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public Mono<User> update(@PathVariable int id, @RequestBody UserRequest request) {
        User user = new User();
        user.setId(id);
        user.setName(request.getName());
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable int id) {
        return userService.deleteById(id);
    }

    @Data
    public static class UserRequest {
        private String name;
    }
}
