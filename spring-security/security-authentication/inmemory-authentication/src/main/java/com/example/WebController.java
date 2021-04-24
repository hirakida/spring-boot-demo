package com.example;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("grantedAuthorities", userDetails.getAuthorities());
        return "home";
    }

    @GetMapping("/admin1")
    public String admin1(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("userDetails", userDetails);
        return "admin";
    }

    @PreAuthorize("hasRole(T(com.example.Role).ADMIN.name())")
    @GetMapping("/admin2")
    public String admin2(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("userDetails", userDetails);
        return "admin";
    }
}
