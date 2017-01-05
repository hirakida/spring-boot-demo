package com.example;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @Autowired
    MessageSource messageSource;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "thymeleaf-tomcat sample");
        return "index";
    }

    // MessageSourceのテスト
    @GetMapping("/message_source")
    public String messageSource(Model model, Locale locale) {
        String code1 = "message1";
        String code2 = "message2";
        String code3 = "message3";  // not found
        String defaultMessage = "not found";
        DefaultMessageSourceResolvable args1 = new DefaultMessageSourceResolvable(new String[] { "args1" });
        DefaultMessageSourceResolvable[] args = { args1 };

        // MessageSourceを使用してStringに変換する
        // codeがない場合はNoSuchMessageExceptionが発生するのでdefaultMessageを指定する
        String message1 = messageSource.getMessage(code1, args, locale);
        String message2 = messageSource.getMessage(code2, args, locale);
        String message3 = messageSource.getMessage(code3, args, defaultMessage, locale);
        model.addAttribute("message1_1", message1);
        model.addAttribute("message1_2", message2);
        model.addAttribute("message1_3", message3);

        // DefaultMessageSourceResolvableを使う
        message1 = messageSource.getMessage(
                new DefaultMessageSourceResolvable(new String[] { code1 }, args), locale);
        message2 = messageSource.getMessage(
                new DefaultMessageSourceResolvable(new String[] { code2 }, args), locale);
        message3 = messageSource.getMessage(
                new DefaultMessageSourceResolvable(new String[] { code3 }, args, defaultMessage), locale);
        model.addAttribute("message2_1", message1);
        model.addAttribute("message2_2", message2);
        model.addAttribute("message2_3", message3);

        return "message_source";
    }
}
