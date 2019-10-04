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
public class AppController {
    private static final String MESSAGE1 = "app.message1";
    private static final String MESSAGE2 = "app.message2";
    private static final String MESSAGE3 = "app.message3";
    private static final String ARGS1 = "app.args1";
    private final MessageSource messageSource;

    @GetMapping("/")
    public String index(Model model, Locale locale) {

        String[] args1 = { "guest" };
        String message1_1 = messageSource.getMessage(MESSAGE1, args1, locale);
        String message2_1 = messageSource.getMessage(MESSAGE2, args1, locale);
        String message3_1 = messageSource.getMessage(MESSAGE3, args1, "not found", locale);
        model.addAttribute("message1_1", message1_1);
        model.addAttribute("message2_1", message2_1);
        model.addAttribute("message3_1", message3_1);

        // DefaultMessageSourceResolvable
        DefaultMessageSourceResolvable[] args = { new DefaultMessageSourceResolvable(new String[] { ARGS1 }) };
        String message1_2 = messageSource.getMessage(MESSAGE1, args, locale);
        String message2_2 = messageSource.getMessage(MESSAGE2, args, locale);
        String message3_2 = messageSource.getMessage(MESSAGE3, args, "not found", locale);
        model.addAttribute("message1_2", message1_2);
        model.addAttribute("message2_2", message2_2);
        model.addAttribute("message3_2", message3_2);

        String[] codes1 = { MESSAGE1 };
        String[] codes2 = { MESSAGE2 };
        String[] codes3 = { MESSAGE3 };
        String message1_3 = messageSource.getMessage(new DefaultMessageSourceResolvable(codes1, args), locale);
        String message2_3 = messageSource.getMessage(new DefaultMessageSourceResolvable(codes2, args), locale);
        String message3_3 = messageSource.getMessage(
                new DefaultMessageSourceResolvable(codes3, args, "not found"), locale);
        model.addAttribute("message1_3", message1_3);
        model.addAttribute("message2_3", message2_3);
        model.addAttribute("message3_3", message3_3);

        // Formatter
        DefaultMessageSourceResolvable resolvable1 = new DefaultMessageSourceResolvable(codes1, args);
        DefaultMessageSourceResolvable resolvable2 = new DefaultMessageSourceResolvable(codes2, args);
        DefaultMessageSourceResolvable resolvable3 = new DefaultMessageSourceResolvable(codes3, args,
                                                                                        "not found");
        model.addAttribute("message1_4", resolvable1);
        model.addAttribute("message2_4", resolvable2);
        model.addAttribute("message3_4", resolvable3);

        return "index";
    }
}
