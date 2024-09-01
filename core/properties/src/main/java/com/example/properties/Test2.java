package com.example.properties;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Test2 {
    @NotNull
    private String text;
}
