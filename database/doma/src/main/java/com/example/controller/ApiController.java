package com.example.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.service.UserService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final UserService userService;

    /**
     * http://localhost:8080/users?page=0&size=2
     */
    @GetMapping("/users")
    public List<User> findAll(@PageableDefault Pageable pageable) {
        return userService.findAll(pageable);
    }

    @GetMapping("/users/{id}")
    public User findOne(@PathVariable long id) {
        return userService.findOne(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public int create(@RequestBody @Validated UserRequest request) {
        return userService.create(request.getName(), request.getAge());
    }

    @PutMapping("/users/{id}")
    public int update(@PathVariable long id, @RequestBody @Validated UserRequest request) {
        return userService.update(id, request.getName(), request.getAge());
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }

    @Data
    public static class UserRequest {
        private @NotNull @Length(max = 30) String name;
        private @NotNull Integer age;
    }
}
