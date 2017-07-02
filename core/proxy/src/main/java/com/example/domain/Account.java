package com.example.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {
    private long id;
    private String name;
}
