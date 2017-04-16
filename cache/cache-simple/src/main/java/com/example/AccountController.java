package com.example;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    final AccountService accountService;

    @GetMapping
    public String get(Model model) {
        List<Account> accounts = accountService.cacheableAll();
        model.addAttribute("accounts", accounts);
        return "account";
    }

    @DeleteMapping
    public String delete() {
        accountService.cacheEvictAll();
        return "account";
    }

    @GetMapping("/{accountId}")
    public String get(@PathVariable long accountId, Model model) {
        Account account = accountService.cacheable(accountId);
        model.addAttribute("account", account);
        return "account";
    }

    @PutMapping("/{accountId}")
    public String put(@PathVariable long accountId, Model model) {
        Account account = accountService.cachePut(accountId);
        model.addAttribute("account", account);
        return "account";
    }

    @DeleteMapping("/{accountId}")
    public String delete(@PathVariable long accountId) {
        accountService.cacheEvict(accountId);
        return "account";
    }
}
