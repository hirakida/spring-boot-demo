package com.example.controller

import com.example.domain.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class WebController {

    @Autowired
    AccountRepository accountRepository

    @GetMapping("/")
    def index(Model model) {
        model.addAttribute("message", "hello!")
        return "index"
    }

    @GetMapping("/accounts")
    def accounts(Model model) {
        def accounts = accountRepository.findAll()
        model.addAttribute("accounts", accounts)
        return "account"
    }

    @GetMapping("/accounts/{id}")
    def account(@PathVariable Integer id, Model model) {
        def account = accountRepository.findOne(id)
        model.addAttribute("accounts", [account])
        return "account"
    }
}
