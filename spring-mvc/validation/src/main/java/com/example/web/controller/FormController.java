package com.example.web.controller;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.web.model.Account;
import com.example.web.validator.CountryCode;
import com.example.web.validator.TelNo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
public class FormController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("form", new Form());
        return "index";
    }

    @PostMapping("/")
    public String post(@Validated Form form, BindingResult result, Model model,
                       RedirectAttributes redirectAttributes) {
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

    @GetMapping("/search")
    public String search(@Validated SearchParams params, Model model) {
        model.addAttribute("form", params.toForm());
        return "index";
    }

    @Data
    @NoArgsConstructor
    public static class Form {
        @NotNull
        @Length(max = 20)
        private String name;
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

    @Data
    @NoArgsConstructor
    public static class Address {
        @NotNull
        private String address1;
        private String address2;
    }

    @Data
    @NoArgsConstructor
    public static class SearchParams {
        @NotNull
        @Length(max = 20)
        private String name;
        @Nullable
        @Range(min = 18, max = 60)
        private Integer age;

        public Form toForm() {
            Form form = new Form();
            form.setName(name);
            form.setAge(age);
            return form;
        }
    }
}
