package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    @ModelAttribute("key")
    public String key() {
        return "key1";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "model");
        return "index";
    }

    @GetMapping("/modelMap")
    public String modelMap(ModelMap modelMap) {
        modelMap.put("message", "modelMap");
        return "index";
    }

    @GetMapping("/modelAndView")
    public ModelAndView modelAndView(ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("message", "modelAndView");
        return mav;
    }
}
