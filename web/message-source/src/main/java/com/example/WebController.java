package com.example;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class WebController {

    private static final String MESSAGE1 = "app.message1";
    private static final String MESSAGE2 = "app.message2";
    private static final String MESSAGE3 = "app.message3";
    private static final String ARGS1 = "app.args1";
    private final MessageSource messageSource;

    public WebController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ModelAttribute("message0")
    public String modelKey(Locale locale) {
        return messageSource.getMessage(MESSAGE1, new String[] { "guest" }, locale);
    }

    @GetMapping("/")
    public String messageSource(Model model, Locale locale) {
        DefaultMessageSourceResolvable[] args = { new DefaultMessageSourceResolvable(new String[] { ARGS1 }) };
        String message1 = messageSource.getMessage(MESSAGE1, args, locale);
        String message2 = messageSource.getMessage(MESSAGE2, args, locale);
        String message3 = messageSource.getMessage(MESSAGE3, args, "not found", locale);
        model.addAttribute("message1", message1);
        model.addAttribute("message2", message2);
        model.addAttribute("message3", message3);
        return "index";
    }

    @GetMapping("/2")
    public String messageSource2(Model model, Locale locale) {
        DefaultMessageSourceResolvable[] args = { new DefaultMessageSourceResolvable(new String[] { ARGS1 }) };
        DefaultMessageSourceResolvable resolvable1 =
                new DefaultMessageSourceResolvable(new String[] { MESSAGE1 }, args);
        DefaultMessageSourceResolvable resolvable2 =
                new DefaultMessageSourceResolvable(new String[] { MESSAGE2 }, args);
        DefaultMessageSourceResolvable resolvable3 =
                new DefaultMessageSourceResolvable(new String[] { MESSAGE3 }, args, "not found");
        String message1 = messageSource.getMessage(resolvable1, locale);
        String message2 = messageSource.getMessage(resolvable2, locale);
        String message3 = messageSource.getMessage(resolvable3, locale);
        model.addAttribute("message1", message1);
        model.addAttribute("message2", message2);
        model.addAttribute("message3", message3);
        return "index";
    }

    @GetMapping("/3")
    public String formatter(Model model) {
        DefaultMessageSourceResolvable[] args = { new DefaultMessageSourceResolvable(new String[] { ARGS1 }) };
        DefaultMessageSourceResolvable resolvable1 =
                new DefaultMessageSourceResolvable(new String[] { MESSAGE1 }, args);
        DefaultMessageSourceResolvable resolvable2 =
                new DefaultMessageSourceResolvable(new String[] { MESSAGE2 }, args);
        DefaultMessageSourceResolvable resolvable3 =
                new DefaultMessageSourceResolvable(new String[] { MESSAGE3 }, args, "not found");
        model.addAttribute("message1", resolvable1);
        model.addAttribute("message2", resolvable2);
        model.addAttribute("message3", resolvable3);
        return "index";
    }
}
