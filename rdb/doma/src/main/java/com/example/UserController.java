package com.example;

import java.util.List;
import java.util.NoSuchElementException;

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

import com.example.entity.User;

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
    private final UserService userService;

    /**
     * http://localhost:8080/users?page=0&size=2
     */
    @GetMapping
    public List<User> findAll(@PageableDefault Pageable pageable) {
        return userService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable long id) {
        return userService.findOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int create(@RequestBody @Validated UserRequest request) {
        return userService.create(request.toUser());
    }

    @PutMapping("/{id}")
    public int update(@PathVariable long id, @RequestBody @Validated UserRequest request) {
        return userService.update(id, request.toUser());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        userService.delete(id);
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
