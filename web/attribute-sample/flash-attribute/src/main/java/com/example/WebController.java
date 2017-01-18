package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * flash scope
 * request scopeとほぼ同等だが、redirect後も状態が保持される
 */
@Controller
public class WebController {

    // redirect前
    @GetMapping("/")
    public String index(RedirectAttributes redirectAttributes) {
        Account account = Account.of(1L);
        // flash attributeにaccountを格納する
        redirectAttributes.addFlashAttribute("account", account);
        return "redirect:/redirect";
    }

    // redirect後
    @GetMapping("/redirect")
    public String redirect(Model model) {
        // リロードするとModelから削除されている
        if (!model.containsAttribute("account")) {
            return "redirect:/";
        }
        // テンプレートでaccountを表示できる
        return "index";
    }
}
