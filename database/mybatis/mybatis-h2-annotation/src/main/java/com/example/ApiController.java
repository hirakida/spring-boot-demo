package com.example;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final AccountMapper accountMapper;

    @GetMapping("/accounts")
    public List<Account> findAll(@PageableDefault Pageable pageable) {
        return accountMapper.findAll(toRowBounds(pageable));
    }

    @GetMapping("/accounts/{id}")
    public Account findOne(@PathVariable long id) {
        return accountMapper.findOne(id);
    }

    @PostMapping("/accounts")
    public void create(@RequestBody Account account) {
        accountMapper.insert(account);
    }

    @PutMapping("/accounts/{id}")
    public void update(@PathVariable long id, @RequestBody Account account) {
        accountMapper.update(account);
    }

    @DeleteMapping("/accounts/{id}")
    public void delete(@PathVariable long id) {
        accountMapper.delete(id);
    }

    public static RowBounds toRowBounds(Pageable pageable) {
        return new RowBounds(pageable.getOffset(), pageable.getPageSize());
    }
}
