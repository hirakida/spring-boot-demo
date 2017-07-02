package com.example

import com.example.domain.Account
import com.example.domain.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class Application implements CommandLineRunner {

    @Autowired
    AccountRepository accountRepository

    @Override
    void run(String... args) throws Exception {

        accountRepository.deleteAll()
        for (i in 0..9) {
            def account = new Account()
            account.setName("account" + i)
            accountRepository.save(account)
        }

        def accounts = accountRepository.findAll()
        accounts.each { account ->
            account.setName(account.getName() + "_")
            accountRepository.save(account)
        }
    }

    static void main(String[] args) {
        SpringApplication.run Application, args
    }
}
