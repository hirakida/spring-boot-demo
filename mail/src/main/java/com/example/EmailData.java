package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailData {
    private String to;
    private String name;
    private String message;
}
