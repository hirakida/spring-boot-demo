package com.example;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.modelmapper.ModelMapper;
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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
@AllArgsConstructor
public class WebController {

    final ModelMapper modelMapper;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Stringは未入力の場合にnullではなく空文字が代入されるため、空文字をnullに変換する
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String get(Model model) {
        model.addAttribute("form", new Form());
        return "index";
    }

    // @ValidatedはBean Validationのバリデーショングループの仕組みが利用できる
    // BindingResultは入力チェックするformの直後に指定する
    @PostMapping(value = "/")
    public String post(@Validated Form form, BindingResult result,
                       Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("form", form);
            return "index";
        }
        // copy
        Account account = new Account();
        BeanUtils.copyProperties(form, account);
//        account = modelMapper.map(form, Account.class);

        // flash attribute
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
        @Length(max = 20)   // @Size(max = 20) でも同じ
        private String name;

        @NotNull
//        @Min(18)
//        @Max(60)
        @Range(min = 18, max = 60)
        private Integer age;

        @Email
        private String email;

        // 追加したアノテーション
        @CountryCode(notEmpty = true)
        private String countryCode;

        //        @CreditCardNumber
        private String card;

        // ネストしている場合は@Validを付ける
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
