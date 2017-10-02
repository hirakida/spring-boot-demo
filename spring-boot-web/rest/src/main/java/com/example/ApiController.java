package com.example;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * HttpMessageConverterがhttp request/responseのbody部とJava Objectの相互変換を行う
 */
@RestController     // @Controller + @ResponseBody
@AllArgsConstructor
@Slf4j
public class ApiController {

    private final AccountService accountService;

    @GetMapping("/accounts/{id}")
    public Account get(@PathVariable long id) {
        return accountService.findById(id);
    }

    @GetMapping("/v2/accounts/{id}")
    public ResponseEntity<Account> getEntity(@PathVariable long id) {
        Account account = accountService.create(id, Gender.FEMALE);
        // Http response headerやHttp statusも指定する場合は、戻り値をResponseEntityにする
        return ResponseEntity.ok()
                             .header("headerName" + id, "headerValue" + id)
                             .body(account);
    }

    @PostMapping("/accounts")
    public Account post(@RequestBody @Validated Account account) {
        account.setId(3L);
        account.setCreatedAt(LocalDateTime.now());
        return account;
    }
}
