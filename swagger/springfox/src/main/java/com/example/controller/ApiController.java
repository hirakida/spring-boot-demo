package com.example.controller;

import java.net.URI;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Get account list", notes = "notes ...")
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    @ApiOperation("Get account")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = Account.class),
            @ApiResponse(code = 404, message = "Not Found Account")
    })
    public Account findById(@PathVariable int id) {
        return accountRepository.findById(id).orElseThrow();
    }

    @GetMapping(value = "/accounts/", params = "name")
    @ApiIgnore
    public List<Account> findByName(@RequestParam String name) {
        return accountRepository.findByName(name);
    }

    @PostMapping("/accounts")
    @ApiOperation("Create account")
    public ResponseEntity<Void> create(@RequestBody @Validated Request request) {
        Account account = new Account();
        account.setName(request.getName());
        accountRepository.save(account);
        return ResponseEntity.created(buildUri(account.getId())).build();
    }

    @PutMapping("/accounts/{id}")
    @ApiOperation("Update account")
    public Account update(@PathVariable int id, @RequestBody @Validated Request request) {
        Account account = accountRepository.findById(id).orElseThrow();
        account.setName(request.getName());
        return accountRepository.save(account);
    }

    @DeleteMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete account")
    public void delete(@PathVariable int id) {
        accountRepository.deleteById(id);
    }

    private static URI buildUri(int id) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                                          .path("/api/accounts/" + id)
                                          .build()
                                          .toUri();
    }

    @Data
    public static class Request {
        private @NotNull String name;
    }
}
