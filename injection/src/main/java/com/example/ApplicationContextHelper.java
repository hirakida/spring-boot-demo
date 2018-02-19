package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationContextHelper {

    public static <T> T getBeanByComponentScan(Class<T> clazz) {
        ApplicationContext app = new AnnotationConfigApplicationContext("com.example");
        return app.getBean(clazz);
    }

    /**
     * from Java Config
     */
    public static <T> T getBeanFromConfig(Class<T> clazz) {
        ApplicationContext app = new AnnotationConfigApplicationContext(BeanConfig.class);
        return app.getBean(clazz);
    }

    public static Object getBeanFromConfig(String beanName) {
        ApplicationContext app = new AnnotationConfigApplicationContext(BeanConfig.class);
        return app.getBean(beanName);
    }

    public static <T> T getBeanFromConfig(Class<T> clazz, String beanName) {
        ApplicationContext app = new AnnotationConfigApplicationContext(BeanConfig.class);
        return app.getBean(beanName, clazz);
    }

    /**
     * from XML
     */
    public static <T> T getBeanFromXml(Class<T> clazz) {
        ApplicationContext app = new ClassPathXmlApplicationContext("bean.xml");
        return app.getBean(clazz);
    }

    public static Object getBeanFromXml(String beanName) {
        ApplicationContext app = new ClassPathXmlApplicationContext("bean.xml");
        return app.getBean(beanName);
    }

    public static <T> T getBeanFromXml(Class<T> clazz, String beanName) {
        ApplicationContext app = new ClassPathXmlApplicationContext("bean.xml");
        return app.getBean(beanName, clazz);
    }
}
