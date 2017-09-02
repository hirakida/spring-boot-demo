package com.example;

import java.util.List;
import java.util.stream.Collectors;

import org.seasar.doma.boot.Pageables;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ApiController {

    final AccountRepository accountRepository;

    /**
     * http://localhost:8080/api/accounts?page=0&size=2
     */
    @GetMapping("/api/accounts")
    public List<Account> accounts(@PageableDefault Pageable pageable) {
        return accountRepository.findAll(Pageables.toSelectOptions(pageable));
    }

    @PutMapping("/api/accounts")
    public List<Account> updateAll() {
        List<Account> accounts =
        accountRepository.findAll().stream()
                         .peek(account -> account.setName(account.getName() + '_'))
                         .collect(Collectors.toList());
        accountRepository.updateNames(accounts);
        return accounts;
    }
}
