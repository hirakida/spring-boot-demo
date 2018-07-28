package com.example;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@SuppressWarnings("serial")
public class User implements Serializable {
    private long id;
    private String name;
    private LocalDateTime createdAt;
}
