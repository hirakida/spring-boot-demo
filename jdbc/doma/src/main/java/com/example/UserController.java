package com.example;

import java.util.List;
import java.util.NoSuchElementException;

import org.seasar.doma.boot.Pageables;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserRepository userRepository;

    @GetMapping
    public List<User> findAll(@PageableDefault Pageable pageable) {
        return userRepository.findAll(Pageables.toSelectOptions(pageable));
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable long id) {
        return userRepository.findOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int create(@RequestBody @Validated UserRequest request) {
        return userRepository.insert(request.toUser());
    }

    @PutMapping("/{id}")
    public int update(@PathVariable long id, @RequestBody @Validated UserRequest request) {
        User user = userRepository.findOne(id);
        user.setName(request.getName());
        user.setAge(request.getAge());
        return userRepository.update(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        User user = userRepository.findOne(id);
        userRepository.delete(user);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Void> handleNoSuchElementException(NoSuchElementException e) {
        log.warn("{}", e.getMessage(), e);
        return ResponseEntity.notFound().build();
    }

    @Data
    public static class UserRequest {
        @NotNull
        @Size(max = 30)
        private String name;
        @NotNull
        private Integer age;

        public User toUser() {
            User user = new User();
            user.setName(name);
            user.setAge(age);
            return user;
        }
    }
}
