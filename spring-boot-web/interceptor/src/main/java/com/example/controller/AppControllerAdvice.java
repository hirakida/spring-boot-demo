package com.example.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller共通の処理
 * 定義できるメソッドは、@InitBinder、@ExceptionHandler、@ModelAttribute
 */
@ControllerAdvice
@Slf4j
public class AppControllerAdvice {

    /**
     * 型変換の初期化
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        log.info("initBinder");
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
//        binder.addCustomFormatter();
    }
}
