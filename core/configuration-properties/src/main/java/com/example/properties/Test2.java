package com.example.properties;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Test2 {
    @NotNull
    private String text;
}
