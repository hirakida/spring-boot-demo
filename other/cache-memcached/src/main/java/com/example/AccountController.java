package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/{accountId}")
    public String get(@PathVariable long accountId, Model model) {
        Account account = accountService.cacheable(accountId);
        model.addAttribute("account", account);
        return "account";
    }

    @DeleteMapping("/{accountId}")
    public String delete(@PathVariable long accountId) {
        accountService.cacheEvict(accountId);
        return "account";
    }
}
