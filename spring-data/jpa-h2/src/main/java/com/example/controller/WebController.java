package com.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.domain.Account;
import com.example.domain.AccountRepository;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class WebController {

    final AccountRepository accountRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Account> accounts = accountRepository.findAll();
        model.addAttribute("accounts", accounts);
        return "index";
    }
}
