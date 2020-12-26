package com.example.controller.controller;

import java.net.URI;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;
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

import com.example.core.entity.Gender;
import com.example.core.entity.User;
import com.example.core.service.UserService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@Validated
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable @Range(max = 10) Integer id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<Void> create(@RequestBody @Validated UserRequest request) {
        User user = userService.create(request.toUser());
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                             .path("/users/" + user.getId())
                                             .build()
                                             .toUri();
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

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRequest {
        @NotNull
        private String name;
        @NotNull
        private Gender gender;
        private String card;

        public User toUser() {
            User user = new User();
            BeanUtils.copyProperties(this, user);
            return user;
        }
    }
}
