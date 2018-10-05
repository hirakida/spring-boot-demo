package com.example;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class SessionBean implements Serializable {
    private String uuid;
    private LocalDateTime localDateTime;
}
