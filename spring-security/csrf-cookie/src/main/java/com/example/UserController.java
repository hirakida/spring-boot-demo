package com.example;

import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {
    @PostMapping("/users")
    public void post(@RequestBody @Validated UserForm form) {
        log.info("{}", form);
    }

    @Data
    private static class UserForm {
        @NotEmpty
        private String lastName;
        @NotEmpty
        private String firstName;
    }
}
