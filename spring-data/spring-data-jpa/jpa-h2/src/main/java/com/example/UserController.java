package com.example;

import java.util.NoSuchElementException;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public Page<User> findAll(@PageableDefault Pageable pageable) {
        return userService.findAll(pageable);
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable int id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody @Validated UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        return userService.create(user);
    }

    @PutMapping("/users/{id}")
    public User update(@PathVariable int id, @RequestBody @Validated UserRequest request) {
        User user = new User();
        user.setId(id);
        user.setName(request.getName());
        return userService.update(user);
    }

    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        userService.deleteAll();
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Void> handleNoSuchElementException() {
        return ResponseEntity.notFound().build();
    }

    @Data
    public static class UserRequest {
        private @NotNull String name;
    }
}
