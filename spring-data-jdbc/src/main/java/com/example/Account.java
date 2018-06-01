package com.example;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Account {
    @Id
    private int id;
    private String name;
}
