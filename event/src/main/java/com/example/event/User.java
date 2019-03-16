package com.example.event;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@SuppressWarnings("serial")
public class User implements Serializable {
    private long id;
    private String name;
}
