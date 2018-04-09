package com.example;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.core.Account;
import com.example.core.AccountRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api")
@Api(description = "This controller is for Account.")
@RequiredArgsConstructor
public class ApiController {

    private final AccountRepository accountRepository;

    @GetMapping("/accounts")
    @ApiOperation(value = "get account list", notes = "notes ...")
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    @ApiOperation("get account")
    public Account findById(@PathVariable int id) {
        return accountRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/accounts")
    @ApiOperation("create account")
    public Account create(@RequestBody @Validated AccountRequest request) {
        Account account = new Account();
        account.setName(request.getName());
        return accountRepository.save(account);
    }

    @PutMapping("/accounts/{id}")
    @ApiOperation("update account")
    public Account update(@PathVariable int id,
                          @RequestBody @Validated AccountRequest request) {
        Account account =
                accountRepository.findById(id)
                                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        account.setName(request.getName());
        return accountRepository.save(account);
    }

    @DeleteMapping("/accounts/{id}")
    @ApiOperation("delete account")
    public void delete(@PathVariable int id) {
        accountRepository.deleteById(id);
    }

    @ApiIgnore
    public void dummy() {
        // dummy
    }

    @Data
    public static class AccountRequest {
        private @NotNull String name;
    }
}
