package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.controller.form.UserForm;
import com.example.model.User;

@Controller
public class UserController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "index";
    }

    @PostMapping("/")
    public String create(@Validated UserForm form, BindingResult result, Model model,
                         RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("userForm", form);
            return "index";
        }

        User user = new User();
        BeanUtils.copyProperties(form, user);
        BeanUtils.copyProperties(form.getAddress(), user);
        attributes.addFlashAttribute("user", user);
        return "redirect:/complete";
    }

    @GetMapping("/complete")
    public String complete() {
        return "complete";
    }
}
