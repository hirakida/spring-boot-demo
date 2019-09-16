package com.example;

import java.time.ZoneId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateTimeRequest {
    private ZoneId zoneId;
}
