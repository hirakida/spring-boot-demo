package com.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Account {
    private String name;
    private String countryCode;
    private Integer age;
    private String email;
    private String telNo;
    private String card;
    private String address1;
    private String address2;
}
