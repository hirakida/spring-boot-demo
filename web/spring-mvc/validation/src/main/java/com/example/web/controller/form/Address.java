package com.example.web.controller.form;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {
    @NotNull
    private String address1;
    private String address2;
}
