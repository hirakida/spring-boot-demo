package com.example.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.annotation.CountryCode;
import com.example.entity.Account;

import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
public class WebController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Convert empty string to null.
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String get(Model model) {
        model.addAttribute("form", new Form());
        return "index";
    }

    @PostMapping("/")
    public String post(@Validated Form form, BindingResult result,
                       Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("form", form);
            return "index";
        }
        Account account = new Account();
        BeanUtils.copyProperties(form, account);
        BeanUtils.copyProperties(form.getAddress(), account);
        redirectAttributes.addFlashAttribute("account", account);
        return "redirect:/complete";
    }

    @GetMapping("/complete")
    public String complete() {
        return "complete";
    }

    @Data
    @NoArgsConstructor
    public static class Form {
        @NotNull
        @Length(max = 20)   // same as @Size(max = 20)
        private String name;
        @NotNull
        @Range(min = 18, max = 60)
        private Integer age;
        @Email
        private String email;
        @CountryCode(notEmpty = true)
        private String countryCode;
        @CreditCardNumber
        private String card;
        @Valid
        private Address address;
    }

    @Data
    @NoArgsConstructor
    public static class Address {
        @NotNull
        private String address1;
        private String address2;
    }
}