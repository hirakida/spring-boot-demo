package com.example;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Account;
import com.example.demo.AccountRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class ApiController {

    private final AccountRepository accountRepository;

    /**
     * pageとsizeを指定できる
     * 省略した場合はdefault値が設定される
     */
    @GetMapping
    public Page<Account> findAll(@PageableDefault Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Account findOne(@PathVariable int id) {
        // fineOneはentityがない場合はnullが返る
        return accountRepository.findOne(id);
    }

//    @GetMapping("/{id}")
    public Account getOne(@PathVariable int id) {
        // getOneはentityがない場合はLazyInitializationExceptionがthrowされる
        return accountRepository.getOne(id);
    }

    @PostMapping
    public Account add(@Validated Form form) {
        Account account = new Account();
        account.setName(form.getName());
        return accountRepository.save(account);
    }

    @PutMapping("/{id}")
    public Account update(@PathVariable int id, @Validated Form form) {
        Account account = accountRepository.findOne(id);
        account.setName(form.getName());
        return accountRepository.save(account);
    }

    @DeleteMapping
    public void deleteAll() {
        accountRepository.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        accountRepository.delete(id);
    }

    @Data
    private static class Form {
        @NotEmpty
        private String name;
    }
}
