package com.example.controller;

import java.net.URI;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    public User getOne(@PathVariable int id) {
        return userRepository.getOne(id);
    }

    @PostMapping("/users")
    public ResponseEntity<Void> create(@RequestBody @Validated UserForm form) {
        User user = userRepository.save(form.toUser());
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                             .path("/users/" + user.getId()).build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/users/{id}")
    public User update(@PathVariable int id, @RequestBody @Validated UserForm form) {
        User user = userRepository.getOne(id);
        BeanUtils.copyProperties(form, user);
        return userRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserForm {
        private @NotEmpty String name;
        private @NotNull Gender gender;
        private String card;

        public User toUser() {
            User user = new User();
            BeanUtils.copyProperties(this, user);
            return user;
        }
    }
}
