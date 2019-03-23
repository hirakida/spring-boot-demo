package com.example.web;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domain.User;
import com.example.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable int id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<Void> create(@RequestBody @Validated UserRequest request) {
        User user = userService.create(request.toUser());
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                             .path("/users/" + user.getId()).build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/users/{id}")
    public User update(@PathVariable int id, @RequestBody @Validated UserRequest request) {
        return userService.update(id, request.toUser());
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }
}
