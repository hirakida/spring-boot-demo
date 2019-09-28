package com.example.client;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class Key implements Serializable {
    private long id;
    private String key;
}
