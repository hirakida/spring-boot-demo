package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    // propertyがない場合はerror
    @Value("${app.string.value1}")
    String str1;

    // propertyがない場合はdefault値が使用される
    @Value("${app.string.value2:default value}")
    String str2;

    @Value("${app.number.value1}")
    long number1;

    @Value("${app.number.value2}")
    long number2;

    @Autowired
    Environment environment;

    @Autowired
    AppProperties properties;

    @GetMapping("/")
    public String index(Model model) {
        // use @Value
        model.addAttribute("str1_1", str1);
        model.addAttribute("str1_2", str2);
        model.addAttribute("number1_1", number1);
        model.addAttribute("number1_2", number2);

        // use Environment
        String str2_1 = environment.getProperty("app.string.value1");
        String str2_2 = environment.getProperty("app.string.value2", String.class, "default value");
        long number2_1 = environment.getProperty("app.number.value2", Long.class);
        long number2_2 = environment.getProperty("app.number.value2", Long.class, 0L);
        model.addAttribute("str2_1", str2_1);
        model.addAttribute("str2_2", str2_2);
        model.addAttribute("number2_1", number2_1);
        model.addAttribute("number2_2", number2_2);

        // use ConfigurationProperties
        model.addAttribute("str3_1", properties.getString().getValue1());
        model.addAttribute("str3_2", properties.getString().getValue2());
        model.addAttribute("number3_1", properties.getNumber().getValue1());
        model.addAttribute("number3_2", properties.getNumber().getValue2());
        return "index";
    }
}
