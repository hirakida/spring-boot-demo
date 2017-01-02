package com.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Account {
    private int id;
    private String name;

    public static Account of(String name) {
        return builder()
                .name(name)
                .build();
    }
}
