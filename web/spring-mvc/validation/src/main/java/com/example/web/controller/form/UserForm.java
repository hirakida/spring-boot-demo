package com.example.web.controller.form;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.example.validator.CountryCode;
import com.example.validator.TelNo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserForm {
    @NotNull
    @Length(max = 10)
    private String lastName;
    @NotNull
    @Length(max = 10)
    private String firstName;
    @NotNull
    @Range(min = 18, max = 60)
    private Integer age;
    @Email
    private String email;
    @CountryCode(notEmpty = true)
    private String countryCode;
    @TelNo
    private String telNo;
    @CreditCardNumber
    private String card;
    @Valid
    private Address address;
}
