package com.example.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonForm {
    @NotNull
    @Length(max = 20)
    private String name;
    @Nullable
    @Range(min = 20, max = 100)
    private Integer age;
}
