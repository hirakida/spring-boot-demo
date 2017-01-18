package com.example;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {
    private long id;
    private String name;
    private String email;

    public static Account of(long id) {
        return builder()
                .id(id)
                .name("name:" + id)
                .email("email:" + id)
                .build();
    }
}
