package com.example;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * RestControllerを定義すると、@Controllerと@ResponseBodyを定義した状態になる
 * HttpMessageConverterがhttp request/responseのbody部とJava Objectの相互変換を行う
 *
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {

    @GetMapping("/accounts/{id}")
    public Account get(@PathVariable long id) {
        if (id > 10L) {
            throw new ForbiddenException("access denied");
        }
        return Account.of(id, Gender.MALE);
    }

    @GetMapping("/accounts/{id}/entity")
    public ResponseEntity<Account> getEntity(@PathVariable long id) {
        Account account = Account.of(id, Gender.FEMALE);
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

    @Data
    @AllArgsConstructor
    public static class Account {
        private long id;
        @NotEmpty
        private String name;
        @NotNull
        private Gender gender;
        private LocalDateTime createdAt;

        public static Account of(long id, Gender gender) {
            return new Account(id, "name" + id, gender, LocalDateTime.now());
        }
    }

    /**
     * ResponseStatusを指定しているので、この例外が投げられると403が返る
     * ResponseStatusを指定しなかった場合は500が返る
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @SuppressWarnings("serial")
    public static class ForbiddenException extends RuntimeException {
        public ForbiddenException(String message) {
            super(message);
        }
    }
}
