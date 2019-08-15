package com.example;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class ApiController {
    private final UserRepository userRepository;

    @GetMapping
    public List<User> findAll() {
        return userRepository.findAllList();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable int id) {
        return userRepository.findById(id).orElseThrow();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody @Validated UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody @Validated UserRequest request, @PathVariable int id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setName(request.getName());
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @Data
    public static class UserRequest {
        private @NotNull String name;
    }
}
