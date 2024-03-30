package com.example.security;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {
    @PostMapping("/users")
    public void post(@RequestBody @Validated UserForm form) {
        log.info("{}", form);
    }

    public record UserForm(@NotEmpty String lastName,
                           @NotEmpty String firstName) {}
}
