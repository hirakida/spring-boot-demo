package com.example;

import java.util.List;

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
        Account account = accountService.findById(accountId);
        model.addAttribute("account", account);
        return "account";
    }

    @GetMapping
    public String get(Model model) {
        List<Account> accounts = accountService.findAll();
        model.addAttribute("accounts", accounts);
        return "account";
    }

    // clear cache
    @DeleteMapping("/{accountId}")
    public String delete(@PathVariable long accountId) {
        accountService.delete(accountId);
        return "account";
    }

    @DeleteMapping
    public String delete() {
        accountService.deleteAll();
        return "account";
    }

    // これはcacheされない
    @GetMapping("/nocache")
    public String get2(Model model) {
        List<Account> accounts = accountService.findById2();
        model.addAttribute("accounts", accounts);
        return "account";
    }
}
