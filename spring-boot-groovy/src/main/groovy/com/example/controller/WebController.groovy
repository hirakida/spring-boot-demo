package com.example.controller

import com.example.core.AccountRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class WebController {

    AccountRepository accountRepository

    WebController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository
    }

    @GetMapping("/")
    def index(Model model) {
        def accounts = accountRepository.findAll()
        model.addAttribute("accounts", accounts)
        return "index"
    }
}
