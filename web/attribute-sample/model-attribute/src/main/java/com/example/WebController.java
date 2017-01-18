package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

/**
 * ModelはControllerとViewとの間で情報を受け渡すのに用いられる
 * String型のkeyと関連付けて画面表示ロジックに受け渡すオブジェクトを格納する
 */
@Controller
public class WebController {

    /**
     * RequestMappingメソッドの実行前に実行され、メソッドの戻り値がModelに保存される
     */
    @ModelAttribute("key")
    public String modelAttribute() {
        return "value";
    }

    /**
     * Modelのattributeとして値を保存すると、Viewで変数として利用できるようになる
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "ModelAttribute sample");
        model.addAttribute("argument", "model");
        return "index";
    }

    @GetMapping("/modelMap")
    public String modelMap(ModelMap modelMap) {
        modelMap.put("message", "ModelAttribute sample");
        // ModelMapのaddAttribute()は、attributeNameをnullチェックしてからput()している
        modelMap.addAttribute("argument", "modelMap");
        return "index";
    }

    /**
     * ModelAndViewはModelとViewをひとまとめにして扱うためのもの
     * Viewとなるファイルの情報と受け渡す値の情報をひとまとめにして管理する
     */
    @GetMapping("/modelAndView")
    public ModelAndView modelAndView(ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("message", "ModelAttribute sample");
        mav.addObject("argument", "modelAndView");
        return mav;
    }
}
