package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebController {

    @GetMapping("/")
    public String index(Model model) {
        Account account = new Account();
        account.setId(1L);
        account.setName("name1");
        account.setEmail("name1@example.com");
        model.addAttribute("account", account);
        return "index";
    }

    @PostMapping("/")
    public String create(Account account, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("account", account);
        return "redirect:/complete";
    }

    @GetMapping("/complete")
    public String complete(Model model) {
        if (!model.containsAttribute("account")) {
            return "redirect:/";
        }
        return "complete";
    }
}
