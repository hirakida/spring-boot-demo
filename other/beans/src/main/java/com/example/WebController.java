package com.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bean.DataBean;

@Controller
public class WebController {

    final DataBean dataBean;

    final LookupHelper lookupHelper;

    // constructor injection
    // Spring 4.3からは@Autowiredを付けなくてよくなった
    public WebController(@Qualifier("dataBean1") DataBean dataBean,
                         LookupHelper lookupHelper) {
        this.dataBean = dataBean;
        this.lookupHelper = lookupHelper;
    }

    @GetMapping("/")
    public String index(Model model) {
        // fieldにinjectionされたbeanを使用
        model.addAttribute("dataBean", dataBean);
        model.addAttribute("componentBean", lookupHelper.getComponentBean());
        return "index";
    }

    @GetMapping("/scan")
    public String scan(Model model) {
        // Java configから取得
        DataBean bean = lookupHelper.getBeanFromComponentScan(DataBean.class);
        model.addAttribute("dataBean", bean);
        model.addAttribute("componentBean", lookupHelper.getComponentBean());
        return "index";
    }

    @GetMapping("/config")
    public String config(Model model) {
        // Java configから取得
        DataBean bean = lookupHelper.getBeanFromConfig(DataBean.class, "dataBean2");
        model.addAttribute("dataBean", bean);
        model.addAttribute("componentBean", lookupHelper.getComponentBean());
        return "index";
    }

    @GetMapping("/xml")
    public String xml(Model model) {
        // xmlから取得
        DataBean bean = lookupHelper.getBeanFromXml(DataBean.class, "dataBeanXml");
        model.addAttribute("dataBean", bean);
        model.addAttribute("componentBean", lookupHelper.getComponentBean());
        return "index";
    }
}
