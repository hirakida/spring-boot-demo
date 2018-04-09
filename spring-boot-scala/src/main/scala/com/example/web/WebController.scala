package com.example.web

import com.example.core.AccountRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class WebController(val accountRepository: AccountRepository) {

  @GetMapping(Array("/"))
  def index(model: Model): String = {
    val accounts = accountRepository.findAll()
    model.addAttribute("accounts", accounts)
    "index"
  }
}
