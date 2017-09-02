package com.example;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultData {
    private String message;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    public static ResultData of(String message, LocalDateTime startAt) {
        ResultData result = new ResultData();
        result.setMessage(message);
        result.setStartAt(startAt);
        return result;
    }
}
