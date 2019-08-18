package com.example.client.model;

import lombok.Data;

@Data
public class DateTime {
    private String time;
    private long milliseconds_since_epoch;
    private String date;
}
