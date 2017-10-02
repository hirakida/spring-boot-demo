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

    @GetMapping("/mypage")
    public String mypage(@AuthenticationPrincipal UserDetails userDetails,
                         HttpServletRequest request,
                         Model model) {
        model.addAttribute("userDetails", userDetails);
        // HttpServletRequestから取得する場合
        // Authenticationが認証情報
        Authentication authentication = (Authentication) request.getUserPrincipal();
        // UserDetailsはユーザー情報
        UserDetails userDetails2 = (UserDetails) authentication.getPrincipal();
        // 認可情報
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        model.addAttribute("authentication", authentication);
        model.addAttribute("userDetails2", userDetails2);
        model.addAttribute("grantedAuthorities", grantedAuthorities);
        return "mypage";
    }

    // Principal型を指定した場合
    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal Principal principal, Model model) {
        UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
        model.addAttribute("userDetails", userDetails);
        return "admin";
    }

    // メソッド単位のアクセス制御
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin2")
    public String admin2(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("userDetails", userDetails);
        return "admin";
    }
}
