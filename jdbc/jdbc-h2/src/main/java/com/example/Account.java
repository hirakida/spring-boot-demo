package com.example;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Account {
    private int id;
    private String name;

    public static Account of(String name) {
        return builder()
                .name(name)
                .build();
    }
}
