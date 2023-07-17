package com.example.controller;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private LocalDateTime start;
    private LocalDateTime end;
}
