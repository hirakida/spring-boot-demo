package com.example.controller;

import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ForbiddenException;
import com.example.Gender;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * RestControllerを定義すると、@Controllerと@ResponseBodyを定義した状態になる
 * HttpMessageConverterがhttp request/responseのbody部とJava Objectの相互変換を行う
 *
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class RestApiController {

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/v1/{id}")
    public Account get1(@PathVariable long id) {
        if (id > 10L) {
            // このexceptionをthrowすると403が返る
            throw new ForbiddenException("access denied");
        }
        return new Account(id, "name" + id, Gender.MALE, LocalDateTime.now());
    }

    /**
     * ResponseEntity
     * Http response headerやHttp statusも指定する場合は、戻り値をResponseEntityにする
     */
    @GetMapping("/v2/{id}")
    public ResponseEntity<Account> get2(@PathVariable long id) {
        Account account = new Account(id, "name" + id, Gender.FEMALE, LocalDateTime.now());
        return ResponseEntity.ok()
                             .header("name" + id, "value" + id)
                             .body(account);
    }

    @GetMapping("/v3/{id}")
    public Response get3(@PathVariable long id) {
        Account account = new Account(id, "name" + id, Gender.FEMALE, LocalDateTime.now());
        Map<String, Object> map = objectMapper.convertValue(account, Map.class);
        return new Response(account, map);
    }

    @PostMapping("/v1")
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

    @Data
    @AllArgsConstructor
    public static class Response {
        private Account account;
        private Map<String, Object> map;
    }
}
