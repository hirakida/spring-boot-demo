package com.example;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

import org.springframework.dao.EmptyResultDataAccessException;
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
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping
    @ApiOperation(value = "Get user list", notes = "notes ...")
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping(params = "name")
    @ApiIgnore
    public List<User> getOneByName(@RequestParam String name) {
        return userRepository.findByName(name);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 404, message = "User Not Found")
    })
    public User getOne(@PathVariable int id) {
        return userRepository.getOne(id);
    }

    @PostMapping
    @ApiOperation("Create user")
    @ApiResponses(@ApiResponse(code = 201, message = "Created"))
    public ResponseEntity<Void> create(@RequestBody @Validated UserRequest request) {
        User user = userRepository.save(request.toUser());
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                             .path("/users/" + user.getId())
                                             .build(false)
                                             .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Update user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 404, message = "User Not Found")
    })
    public User update(@PathVariable int id, @RequestBody @Validated UserRequest request) {
        User user = userRepository.getOne(id);
        user.setName(request.getName());
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete user")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 404, message = "User Not Found")
    })
    public void delete(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @ExceptionHandler({ EntityNotFoundException.class, EmptyResultDataAccessException.class })
    public ResponseEntity<Void> handleNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @Data
    public static class UserRequest {
        @NotNull
        private String name;

        public User toUser() {
            User user = new User();
            user.setName(name);
            return user;
        }
    }
}
