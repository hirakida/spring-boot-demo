package com.example.controller

import com.example.core.Account
import com.example.core.AccountRepository
import org.springframework.web.bind.annotation.*

@RestController
class ApiController {

    AccountRepository accountRepository

    ApiController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository
    }

    @GetMapping("/api/accounts")
    def findAll() {
        return accountRepository.findAll()
    }

    @GetMapping("/api/accounts/{id}")
    def findById(@PathVariable Integer id) {
        return accountRepository.findById(id)
    }

    @PostMapping("/api/accounts")
    def create(@RequestBody Account account) {
        return accountRepository.save(account)
    }

    @PutMapping("/api/accounts/{id}")
    def update(@RequestBody Account account) {
        return accountRepository.save(account)
    }

    @DeleteMapping("/api/accounts/{id}")
    def deleteById(@PathVariable Integer id) {
        accountRepository.deleteById(id)
    }
}
