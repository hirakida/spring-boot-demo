package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.Account;

/**
 * session scope
 */
@Controller
@RequestMapping("/session")
@SessionAttributes("account")
public class SessionController {

    @GetMapping
    public String index(Model model) {
        Account account = Account.of(1L);
        model.addAttribute("account", account);
        return "session";
    }

    @PostMapping
    public String post(@ModelAttribute Account account) {
        // accountはsessionスコープなので、リダイレクト後の画面でも表示できる
        return "redirect:/session/complete";
    }

    @GetMapping("/complete")
    public String complete(SessionStatus sessionStatus, Model model) {
        if (!model.containsAttribute("account")) {
            return "redirect:/session";
        }
        // sessionスコープのオブジェクトを破棄
        sessionStatus.setComplete();
        return "session_complete";
    }
}