package com.example;

import static org.springframework.http.HttpStatus.NOT_FOUND;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<User> findById(@PathVariable String id) {
        return userRepository.findById(id)
                             .switchIfEmpty(Mono.error(new ResponseStatusException(NOT_FOUND)));
    }

    @GetMapping(params = "name")
    public Flux<User> findByName(@RequestParam String name) {
        return userRepository.findByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> create(@RequestBody @Validated UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setAge(request.getAge());
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public Mono<User> update(@PathVariable String id, @RequestBody @Validated UserRequest request) {
        return userRepository.findById(id)
                             .flatMap(user -> {
                                 user.setName(request.getName());
                                 user.setAge(request.getAge());
                                 return userRepository.save(user);
                             })
                             .switchIfEmpty(Mono.error(new ResponseStatusException(NOT_FOUND)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return userRepository.deleteById(id);
    }

    @Data
    public static class UserRequest {
        private @NotNull String name;
        private @NotNull Integer age;
    }
}
