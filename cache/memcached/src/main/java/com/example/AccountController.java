package com.example;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {

    final AccountService accountService;

    @GetMapping
    public String findAll(Model model) {
        List<Account> accounts = accountService.findAll();
        model.addAttribute("accounts", accounts);
        return "account";
    }

    @GetMapping("/{id}")
    public String findOne(@PathVariable int id, Model model) {
        Account account = accountService.findOne(id);
        model.addAttribute("account", account);
        return "account";
    }

    @ResponseBody
    @PostMapping("/clearCache")
    public void clearCache() {
        accountService.clearCache();
    }

    @ResponseBody
    @PostMapping("/{id}/clearCache")
    public void clearCache(@PathVariable int id) {
        accountService.clearCache(id);
    }
}
