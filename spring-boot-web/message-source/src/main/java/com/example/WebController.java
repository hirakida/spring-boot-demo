package com.example;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class WebController {

    private static final String MESSAGE1 = "app.message1";
    private static final String MESSAGE2 = "app.message2";
    private static final String MESSAGE3 = "app.message3";
    private static final String ARGS1 = "app.args1";

    final MessageSource messageSource;

    @ModelAttribute("message1_0")
    public String modelKey(Locale locale) {
        // MessageSourceを使用してStringに変換する
        return messageSource.getMessage(MESSAGE1, new String[] { "guest" }, locale);
    }

    /**
     * messageに埋め込むargsもpropertiesから取得する場合
     */
    @GetMapping("/")
    public String messageSource(Model model, Locale locale) {
        DefaultMessageSourceResolvable[] args = getResolvableArgs();
        String message1 = messageSource.getMessage(MESSAGE1, args, locale);
        String message2 = messageSource.getMessage(MESSAGE2, args, locale);
        // codeがない場合はNoSuchMessageExceptionが発生するのでdefaultMessageを指定する
        String message3 = messageSource.getMessage(MESSAGE3, args, "not found", locale);
        model.addAttribute("message1", message1);
        model.addAttribute("message2", message2);
        model.addAttribute("message3", message3);
        return "index";
    }

    /**
     * DefaultMessageSourceResolvableを使う
     */
    @GetMapping("/2")
    public String messageSource2(Model model, Locale locale) {
        DefaultMessageSourceResolvable[] args = getResolvableArgs();
        DefaultMessageSourceResolvable resolvable1 = getResolvable(MESSAGE1, args);
        DefaultMessageSourceResolvable resolvable2 = getResolvable(MESSAGE2, args);
        DefaultMessageSourceResolvable resolvable3 = getResolvable(MESSAGE3, args, "not found");
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
        DefaultMessageSourceResolvable[] args = getResolvableArgs();
        DefaultMessageSourceResolvable resolvable1 = getResolvable(MESSAGE1, args);
        DefaultMessageSourceResolvable resolvable2 = getResolvable(MESSAGE2, args);
        DefaultMessageSourceResolvable resolvable3 = getResolvable(MESSAGE3, args, "not found");
        // MessageSourceResolvableをtemplateに渡してFormatterでStringに変換する
        model.addAttribute("message1", resolvable1);
        model.addAttribute("message2", resolvable2);
        model.addAttribute("message3", resolvable3);
        return "index";
    }

    private static DefaultMessageSourceResolvable[] getResolvableArgs() {
        DefaultMessageSourceResolvable args1 = new DefaultMessageSourceResolvable(new String[] { ARGS1 });
        return new DefaultMessageSourceResolvable[] { args1 };
    }

    private static DefaultMessageSourceResolvable getResolvable(String code,
                                                                DefaultMessageSourceResolvable[] args) {
        return new DefaultMessageSourceResolvable(new String[] { code }, args);
    }

    private static DefaultMessageSourceResolvable getResolvable(String code,
                                                                DefaultMessageSourceResolvable[] args,
                                                                String defaultMessage) {
        return new DefaultMessageSourceResolvable(new String[] { code }, args, defaultMessage);
    }
}
