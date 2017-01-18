package com.example;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * RestControllerを定義すると、@Controllerと@ResponseBodyを定義した状態になる
 */
@RestController
@RequestMapping("/api/rest")
@Slf4j
public class RestApiController {

    @GetMapping("/1")
    public Account get1() {
        return new Account(1L, "name1", Gender.MALE, LocalDateTime.now());
    }

    // Http response headerやHttp statusも指定する場合は、戻り値をResponseEntityにする
    @GetMapping("/2")
    public ResponseEntity<Account> get2() {
        Account response = new Account(2L, "name2", Gender.FEMALE, LocalDateTime.now());
        HttpHeaders headers = new HttpHeaders();
        headers.add("name", "value");
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping
    public Account post(@RequestBody @Validated Account account) {
        account.setId(3L);
        account.setCreatedAt(LocalDateTime.now());
        return account;
    }

    @Data
    @AllArgsConstructor
    public static class Account {
        private long id;
        @NotEmpty
        private String name;
        @NotNull
        private Gender gender;
        private LocalDateTime createdAt;
    }
}
