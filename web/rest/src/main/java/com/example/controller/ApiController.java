package com.example.controller;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Gender;
import com.example.domain.User;
import com.example.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final UserRepository userRepository;

    @GetMapping("/users")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getOne1(@PathVariable int id) {
        return userRepository.getOne(id);
    }

    @GetMapping("/v2/users/{id}")
    public ResponseEntity<User> getOne2(@PathVariable int id) {
        User user = userRepository.getOne(id);
        return ResponseEntity.ok()
                             .header("User-Id", String.valueOf(id))
                             .body(user);
    }

    @PostMapping("/users")
    public User create(@RequestBody @Validated UserForm form) {
        return userRepository.save(form.toAccount());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserForm {
        private @NotEmpty String name;
        private @NotNull Gender gender;
        private String card;

        public User toAccount() {
            User user = new User();
            BeanUtils.copyProperties(this, user);
            return user;
        }
    }
}
