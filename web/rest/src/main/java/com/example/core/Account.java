package com.example.core;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private long id;
    private String name;
    private Gender gender;
    private Optional<String> card;
    private LocalDateTime createdAt;
}
