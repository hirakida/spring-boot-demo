package com.example

import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/accounts")
class AccountController(private val repository: AccountRepository) {
    @GetMapping
    fun findAll(): Flow<Account> = repository.findAll()

    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id: Int): Account =
        repository.findById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun create(@RequestBody account: Account): Account = repository.save(account)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    suspend fun delete(@PathVariable id: Int) {
        repository.deleteById(id)
    }
}
