package com.example;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.constraints.NotNull;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final UserRepository userRepository;

    @GetMapping("/csrf")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }

    @GetMapping("/users")
    @ApiOperation(value = "Get user list", notes = "notes ...")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    @ApiOperation("Get user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 404, message = "Not Found User")
    })
    public User findById(@PathVariable int id) {
        return userRepository.findById(id).orElseThrow();
    }

    @GetMapping(value = "/users/", params = "name")
    @ApiIgnore
    public List<User> findByName(@RequestParam String name) {
        return userRepository.findByName(name);
    }

    @PostMapping("/users")
    @ApiOperation("Create user")
    public ResponseEntity<Void> create(@RequestBody @Validated UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        userRepository.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                             .path("/api/users/" + user.getId())
                                             .build(false)
                                             .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/users/{id}")
    @ApiOperation("Update user")
    public User update(@PathVariable int id, @RequestBody @Validated UserRequest request) {
        User user = userRepository.findById(id).orElseThrow();
        user.setName(request.getName());
        return userRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete user")
    public void delete(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @ExceptionHandler({ EmptyResultDataAccessException.class, NoSuchElementException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFoundException() {
    }

    @Data
    public static class UserRequest {
        private @NotNull String name;
    }
}
