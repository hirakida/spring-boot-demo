package com.example;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WebController {

    private static final String MESSAGE1 = "app.message1";
    private static final String MESSAGE2 = "app.message2";
    private static final String MESSAGE3 = "app.message3";
    private static final String ARGS1 = "app.args1";
    private final MessageSource messageSource;

    @GetMapping("/")
    public String index(Model model, Locale locale) {

        String message0 = messageSource.getMessage(MESSAGE1, new String[] { "guest" }, locale);
        model.addAttribute("message0", message0);

        DefaultMessageSourceResolvable[] args = { new DefaultMessageSourceResolvable(new String[] { ARGS1 }) };
        String message1_1 = messageSource.getMessage(MESSAGE1, args, locale);
        String message1_2 = messageSource.getMessage(MESSAGE2, args, locale);
        String message1_3 = messageSource.getMessage(MESSAGE3, args, "not found", locale);
        model.addAttribute("message1_1", message1_1);
        model.addAttribute("message1_2", message1_2);
        model.addAttribute("message1_3", message1_3);

        String message2_1 = messageSource.getMessage(
                new DefaultMessageSourceResolvable(new String[] { MESSAGE1 }, args), locale);
        String message2_2 = messageSource.getMessage(
                new DefaultMessageSourceResolvable(new String[] { MESSAGE2 }, args), locale);
        String message2_3 = messageSource.getMessage(
                new DefaultMessageSourceResolvable(new String[] { MESSAGE3 }, args, "not found"), locale);
        model.addAttribute("message2_1", message2_1);
        model.addAttribute("message2_2", message2_2);
        model.addAttribute("message2_3", message2_3);

        DefaultMessageSourceResolvable resolvable1 =
                new DefaultMessageSourceResolvable(new String[] { MESSAGE1 }, args);
        DefaultMessageSourceResolvable resolvable2 =
                new DefaultMessageSourceResolvable(new String[] { MESSAGE2 }, args);
        DefaultMessageSourceResolvable resolvable3 =
                new DefaultMessageSourceResolvable(new String[] { MESSAGE3 }, args, "not found");
        model.addAttribute("message3_1", resolvable1);
        model.addAttribute("message3_2", resolvable2);
        model.addAttribute("message3_3", resolvable3);

        return "index";
    }
}
