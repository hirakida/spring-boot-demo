package com.example;

import java.security.Principal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal UserDetails userDetails,
                       HttpServletRequest request, Model model) {
        Authentication authentication = (Authentication) request.getUserPrincipal();
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        UserDetails userDetails2 = (UserDetails) authentication.getPrincipal();
        log.info("{}", userDetails2);

        model.addAttribute("userDetails", userDetails);
        model.addAttribute("authentication", authentication);
        model.addAttribute("grantedAuthorities", grantedAuthorities);
        return "home";
    }

    // Use Principal
    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal Principal principal, Model model) {
        UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
        model.addAttribute("userDetails", userDetails);
        return "admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/root")
    public String root(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("userDetails", userDetails);
        return "admin";
    }
}
