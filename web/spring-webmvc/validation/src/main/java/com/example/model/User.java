package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private String lastName;
    private String firstName;
    private String countryCode;
    private Integer age;
    private String email;
    private String telNo;
    private String card;
    private String address1;
    private String address2;
}
