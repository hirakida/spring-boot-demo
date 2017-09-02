package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebController {

    /**
     * RequestMappingメソッドの実行前に実行され、メソッドの戻り値がModelに保存される
     */
    @ModelAttribute("key")
    public String modelKey() {
        return "value";
    }

    @GetMapping
    public String index() {
        return "index";
    }

    /**
     * Modelのattributeとして値を保存すると、Viewで変数として利用できるようになる
     * ModelAttributeは自動的にrequestスコープに格納される
     */
    @GetMapping("/model")
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

    /**
     * flash attribute
     */
    @PostMapping("/flash")
    public String post(RedirectAttributes redirectAttributes) {
        // リロードすると削除される
        redirectAttributes.addFlashAttribute("message", "flash attribute");
        return "redirect:/";
    }
}
