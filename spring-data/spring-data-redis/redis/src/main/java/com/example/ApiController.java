package com.example;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.client.StringRedisClient;
import com.example.client.UserRedisClient;
import com.example.model.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final StringRedisClient stringRedisClient;
    private final UserRedisClient userRedisClient;

    @GetMapping("/strings/{key}")
    public String get(@PathVariable String key) {
        return stringRedisClient.get(key)
                                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @PutMapping("/strings/{key}")
    public Boolean expire(@PathVariable String key, @RequestParam long seconds) {
        return stringRedisClient.expire(key, seconds);
    }

    @DeleteMapping("/strings/{key}")
    public void delete(@PathVariable String key) {
        stringRedisClient.delete(key);
    }

    @PutMapping("/strings/{key}/{oldValue}/{newValue}")
    public Boolean set(@PathVariable String key, @PathVariable String oldValue, @PathVariable String newValue) {
        return stringRedisClient.checkAndSet(key, oldValue, newValue);
    }

    @GetMapping("/strings/exists/{key}/{value}")
    public Boolean exists(@PathVariable String key, @PathVariable String value) {
        return stringRedisClient.exists(key, value);
    }

    @GetMapping("/users/{id}")
    public User get(@PathVariable long id) {
        return userRedisClient.get(id)
                              .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable long id) {
        userRedisClient.delete(id);
    }
}
