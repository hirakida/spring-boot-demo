package com.example;

import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class ApiController {
    private final UserRepository userRepository;

    @GetMapping
    public Page<User> findAll(@PageableDefault Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable String id) {
        return userRepository.findById(id)
                             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Validated RequestData request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        userRepository.save(user);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody @Validated RequestData request) {
        userRepository.findById(id)
                      .ifPresent(user -> {
                          user.setName(request.getName());
                          userRepository.save(user);
                      });
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable String id) {
        userRepository.deleteById(id);
    }

    @Data
    public static class RequestData {
        private @NotNull String id;
        private @NotNull String name;
    }
}
