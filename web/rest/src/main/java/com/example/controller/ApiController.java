package com.example.controller;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.core.Account;
import com.example.core.AccountService;
import com.example.core.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final AccountService accountService;

    @GetMapping("/v1/accounts/{id}")
    public Account findById(@PathVariable long id) {
        return accountService.findById(id);
    }

    @GetMapping("/v2/accounts/{id}")
    public ResponseEntity<Account> findByIdWithResponseEntity(@PathVariable long id) {
        Account account = accountService.findById(id);
        return ResponseEntity.ok()
                             .header("headerName" + id, "headerValue" + id)
                             .body(account);
    }

    @PostMapping("/accounts")
    public Account post(@RequestBody @Validated AccountForm form) {
        return accountService.create(form.toAccount());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AccountForm {
        @NotEmpty
        private String name;
        @NotNull
        private Gender gender;
        private Optional<String> card;

        public Account toAccount() {
            Account account = new Account();
            BeanUtils.copyProperties(this, account);
            return account;
        }
    }
}
