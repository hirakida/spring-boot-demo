package com.example.config;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScopeTime {
    private LocalTime time;
}
