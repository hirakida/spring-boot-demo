package com.example.controller

import com.example.domain.Account
import com.example.domain.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController {

    @Autowired
    AccountRepository accountRepository

    @GetMapping("/api/accounts")
    def accounts() {
        return accountRepository.findAll()
    }

    @GetMapping("/api/accounts/{id}")
    def account(@PathVariable Integer id) {
        return accountRepository.findOne(id)
    }

    @PostMapping("/api/accounts")
    def post(@RequestBody Account account) {
        return accountRepository.save(account)
    }

    @PutMapping("/api/accounts/{id}")
    def put(@RequestBody Account account) {
        return accountRepository.save(account)
    }

    @DeleteMapping("/api/accounts/{id}")
    delete(@PathVariable Integer id) {
        accountRepository.delete(id)
    }
}
