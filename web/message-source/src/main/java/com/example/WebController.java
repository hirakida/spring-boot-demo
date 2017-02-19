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
        DefaultMessageSourceResolvable resolvable1 =
                new DefaultMessageSourceResolvable(new String[] { code1 }, args);
        DefaultMessageSourceResolvable resolvable2 =
                new DefaultMessageSourceResolvable(new String[] { code2 }, args);
        DefaultMessageSourceResolvable resolvable3 =
                new DefaultMessageSourceResolvable(new String[] { code3 }, args, defaultMessage);
        model.addAttribute("message2_1", messageSource.getMessage(resolvable1, locale));
        model.addAttribute("message2_2", messageSource.getMessage(resolvable2, locale));
        model.addAttribute("message2_3", messageSource.getMessage(resolvable3, locale));

        // MessageSourceResolvableをtemplateに渡してFormatterでStringに変換する
        model.addAttribute("message3_1", resolvable1);
        model.addAttribute("message3_2", resolvable2);
        model.addAttribute("message3_3", resolvable3);

        return "index";
    }
}
