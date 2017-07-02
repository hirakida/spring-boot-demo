package com.example.domain;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
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
