package com.example;

import java.time.LocalDateTime;
import java.util.Optional;

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
    private Optional<String> card;
    private LocalDateTime createdAt;
}
