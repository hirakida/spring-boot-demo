package com.example;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {
    private long id;
    private String name;

    public static Account of(long id) {
        return of(id, "name" + id);
    }

    public static Account of(long id, String name) {
        return builder()
                .id(id)
                .name(name)
                .build();
    }
}
