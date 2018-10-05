package com.example.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class SessionBean implements Serializable {
    private String id;
    private String uuid;
    private LocalDateTime localDateTime;
}
