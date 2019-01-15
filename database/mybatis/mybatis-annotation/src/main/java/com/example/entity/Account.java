package com.example.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class Account implements Serializable {
    private long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
