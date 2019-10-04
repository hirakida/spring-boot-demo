package com.example;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AppController {
    private static final List<Locale> LOCALES = List.of(Locale.JAPAN, Locale.US, Locale.UK, Locale.FRANCE,
                                                        Locale.SIMPLIFIED_CHINESE, Locale.TRADITIONAL_CHINESE,
                                                        Locale.KOREA);
    private final MessageSource messageSource;

    @GetMapping
    public String index(Model model, Locale locale) {
        String message = messageSource.getMessage("message", null, locale);
        model.addAttribute("locales", LOCALES);
        model.addAttribute("message", message);
        return "index";
    }
}
