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
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {

    final AccountService accountService;

    @GetMapping
    public String getAll(Model model) {
        List<Account> accounts = accountService.cacheableAll();
        model.addAttribute("accounts", accounts);
        return "account";
    }

    @DeleteMapping
    public String deleteAll() {
        accountService.cacheEvictAll();
        return "account";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable long id, Model model) {
        Account account = accountService.cacheable(id);
        model.addAttribute("account", account);
        return "account";
    }

    @PutMapping("/{id}")
    public String put(@PathVariable long id, Model model) {
        Account account = accountService.cachePut(id);
        model.addAttribute("account", account);
        return "account";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        accountService.cacheEvict(id);
        return "account";
    }
}
