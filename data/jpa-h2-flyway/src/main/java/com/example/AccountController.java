package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    /**
     * pageとsizeを指定できる
     * 省略した場合は、デフォルトでpage=0, size=20が入る
     */
    @GetMapping("/accounts")
    public String getAccounts(@PageableDefault Pageable pageable, Model model) {
        Page<Account> accounts = accountRepository.findAll(pageable);
        model.addAttribute("totalElements", accounts.getTotalElements());
        model.addAttribute("totalPages", accounts.getTotalPages());
        accounts.getSize();

        model.addAttribute("accounts", accounts.getContent());
        model.addAttribute("page", accounts);
        return "account";
    }
}
