package com.example.bean;

import lombok.Data;

@Data
public class MyBean {
    private long id;
    private String name;
    private long count;

    public long getCount() {
        return ++count;
    }
}
