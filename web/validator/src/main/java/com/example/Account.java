package com.example;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Account {
    private String name;
    private String countryCode;
    private Integer age;
    private String email;
    private String card;
}
