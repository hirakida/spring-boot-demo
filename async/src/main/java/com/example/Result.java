package com.example;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private LocalDateTime start;
    private LocalDateTime end;
}
