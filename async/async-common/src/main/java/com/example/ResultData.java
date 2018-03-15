package com.example;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ResultData {
    private final String message;
    private final LocalDateTime startAt;
    private LocalDateTime endAt;
}
