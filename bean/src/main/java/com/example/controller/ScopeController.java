package com.example.controller;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bean.ApplicationBean;
import com.example.bean.Prototype1Bean;
import com.example.bean.Prototype2Bean;
import com.example.bean.RequestBean;
import com.example.bean.SessionBean;
import com.example.bean.SingletonBean;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/scope")
@AllArgsConstructor
public class ScopeController {

    Prototype1Bean prototype1Bean;

    Prototype2Bean prototype2Bean;

    RequestBean requestBean;

    SessionBean sessionBean;

    ApplicationBean applicationBean;

    SingletonBean singletonBean;

    @GetMapping
    public String index(Model model) {
        // prototype, requestのcountは常に1
        // proxyの場合、メソッドを呼ぶ度にインスタンスが生成されるのでidとnameは常に空
        prototype1Bean.setId(1);
        prototype1Bean.setName("prototype1");
        prototype2Bean = getPrototype2Bean();
        prototype2Bean.setId(1);
        prototype2Bean.setName("prototype2");
        // requestのcountは常に1
        requestBean.setId(2);
        requestBean.setName("request");
        // sessionはcookieがあればカウントアップ
        sessionBean.setId(3);
        sessionBean.setName("session");
        // application, singletonは常にカウントアップ
        applicationBean.setId(4);
        applicationBean.setName("application");
        singletonBean.setId(5);
        singletonBean.setName("singleton");

        model.addAttribute("prototype1Bean", prototype1Bean);
        model.addAttribute("prototype2Bean", prototype2Bean);
        model.addAttribute("requestBean", requestBean);
        model.addAttribute("sessionBean", sessionBean);
        model.addAttribute("appBean", applicationBean);
        model.addAttribute("singletonBean", singletonBean);
        return "scope";
    }

    /**
     * DIコンテナからBeanをLookupする
     * lookupメソッドは、privateやfinalを付けてはいけない、メソッドの引数も指定してはいけない
     */
    @Lookup
    public Prototype2Bean getPrototype2Bean() {
        // 戻り値はダミーでOK
        return null;
    }
}
