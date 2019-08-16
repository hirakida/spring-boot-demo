package com.example;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.constraints.NotNull;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class ApiController {
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
    public User create(@RequestBody @Validated RequestData request) {
        User user = new User();
        user.setName(request.getName());
        return userRepository.insert(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable int id, @RequestBody @Validated RequestData request) {
        User user = userRepository.findById(id).orElseThrow();
        user.setName(request.getName());
        return userRepository.update(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @ExceptionHandler({ EmptyResultDataAccessException.class, NoSuchElementException.class })
    public ResponseEntity<Object> handleNotFoundException(RuntimeException e) {
        log.warn(e.getMessage(), e);
        return ResponseEntity.notFound().build();
    }

    @Data
    public static class RequestData {
        private @NotNull String name;
    }
}
