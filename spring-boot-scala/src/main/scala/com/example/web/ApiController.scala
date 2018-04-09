package com.example.web

import java.lang.Iterable

import com.example.core.{Account, AccountRepository, DataNotFoundException}
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/api"))
class ApiController(val accountRepository: AccountRepository) {

  @GetMapping(Array("/accounts"))
  def findAll(): Iterable[Account] = accountRepository.findAll()

  @GetMapping(Array("/accounts/{id}"))
  def findById(@PathVariable id: Int): Account = findOne(id)

  @GetMapping(Array("/accounts/name/{name}"))
  def findByName(@PathVariable name: String): Iterable[Account] =
    accountRepository.findByName(name)

  @PostMapping(Array("/accounts"))
  def create(@RequestBody request: ApiAccount): Account =
    accountRepository.save(new Account(request.name))

  @PutMapping(Array("/accounts/{id}"))
  def update(@PathVariable id: Int, @RequestBody request: ApiAccount): Account = {
    val account = findOne(id)
    accountRepository.save(account.copy(name = request.name))
  }

  @DeleteMapping(Array("/accounts/{id}"))
  def delete(@PathVariable id: Int) {
    accountRepository.delete(findOne(id))
  }

  private def findOne(id: Int): Account =
    Option(accountRepository.findOne(id)).getOrElse(throw new DataNotFoundException(id.toString))
}
