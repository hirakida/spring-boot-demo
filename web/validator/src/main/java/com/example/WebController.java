package com.example;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/")
    public String get(Model model) {
        model.addAttribute("form", new Form());
        return "index";
    }

    // @ValidatedはBean Validationのバリデーショングループの仕組みが利用できる
    // BindingResultは入力チェックするformの直後に指定する
    @PostMapping("/")
    public String post(@Validated Form form, BindingResult result,
                       Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("form", form);
            return "index";
        }
        // copy
        Account account1 = new Account();
        BeanUtils.copyProperties(form, account1);
        Account account2 = modelMapper.map(form, Account.class);

        // flash attribute
        redirectAttributes.addFlashAttribute("account", account1);
        return "redirect:/complete";
    }

    @GetMapping("/complete")
    public String complete() {
        return "complete";
    }

    @Data
    @NoArgsConstructor
    private static class Form {
        // Stringは未入力の場合にdefaultで空文字が代入されるため、NotNullは使えない
        @NotEmpty
        @Length(max = 20)   // @Size(max = 20) でも同じ
        private String name;
        // 追加したアノテーション
        @CountryCode(notEmpty = true)
        private String countryCode;
        @NotNull
//        @Min(18)
//        @Max(60)
        @Range(min = 18, max = 60)
        private Integer age;
        @Email
        private String email;
        //        @CreditCardNumber
        private String card;
    }
}
