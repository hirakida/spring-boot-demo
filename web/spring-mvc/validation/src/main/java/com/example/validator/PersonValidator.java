package com.example.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.web.controller.form.Person;

@Component
public class PersonValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "NotEmpty.person.name");
        Person person = (Person) target;
        if (person.getAge() < 20 || person.getAge() > 60) {
            errors.rejectValue("age", "Range.person.age");
        }
    }
}
