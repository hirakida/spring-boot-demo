package com.example.web.controller.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.jetbrains.annotations.Nullable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonParams {
    @NotNull
    @Length(max = 20)
    private String name;
    @Nullable
    @Range(min = 20, max = 60)
    private Integer age;
}
