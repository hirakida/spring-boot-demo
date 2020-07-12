package com.example;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.reactivex.Flowable;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public Flowable<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public Single<User> getById(@PathVariable int id) {
        return userService.findById(id)
                          .switchIfEmpty(Single.error(new ResponseStatusException(NOT_FOUND)));
    }
}
