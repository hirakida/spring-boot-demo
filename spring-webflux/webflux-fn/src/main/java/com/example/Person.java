package com.example;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    private int id;
    private String name;
    private LocalDateTime createdAt;

    public Person(String name) {
        this.name = name;
        createdAt = LocalDateTime.now();
    }
}
