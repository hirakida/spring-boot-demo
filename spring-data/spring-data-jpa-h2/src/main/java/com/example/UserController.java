package com.example;

import java.util.List;

import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository repository;

    @GetMapping("/users")
    public Page<User> findAll(@PageableDefault Pageable pageable) {
        return repository.findAll(pageable);
    }

    @GetMapping(value = "/users", params = "name")
    public List<User> findByName(@RequestParam String name) {
        return repository.findByName(name);
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable("id") User user) {
        return user;
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody @Validated Request request) {
        User user = new User();
        user.setName(request.getName());
        return repository.save(user);
    }

    @PutMapping("/users/{id}")
    public User update(@PathVariable int id, @RequestBody @Validated Request request) {
        User user = repository.findById(id)
                              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.setName(request.getName());
        return repository.save(user);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        User user = repository.findById(id)
                              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repository.delete(user);
    }

    @Data
    public static class Request {
        @NotNull
        private String name;
    }
}
