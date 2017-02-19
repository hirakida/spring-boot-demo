package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * flash scope
 * request scopeとほぼ同等だが、redirect後も状態が保持される
 */
@Controller
@RequestMapping("/flash")
public class FlashController {

    @GetMapping
    public String get() {
        return "flash";
    }

    @PostMapping
    public String post(RedirectAttributes redirectAttributes) {
        Account account = Account.of(1L);
        // flash attributeにaccountを格納する
        redirectAttributes.addFlashAttribute("account", account);
        return "redirect:/flash/complete";
    }

    @GetMapping("/complete")
    public String complete(Model model) {
        // リロードすると削除されている
        if (!model.containsAttribute("account")) {
            return "redirect:/flash";
        }
        // テンプレートでaccountを表示できる
        return "flash";
    }
}
