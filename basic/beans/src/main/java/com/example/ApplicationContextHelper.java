package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationContextHelper {

    /**
     * Java Configから取得
     */
    public static <T> T getBeanFromConfig(Class<T> clazz) {
        // Java Config(Bean定義ファイル)を渡してDIコンテナを生成
        ApplicationContext app = new AnnotationConfigApplicationContext(BeanConfig.class);
        // Beanの型を指定して、DIコンテナからインスタンスを取得
        // 指定した型を持つBeanがDIコンテナに一つだけ存在する場合に使用する
        return app.getBean(clazz);
    }

    public static Object getBeanFromConfig(String beanName) {
        ApplicationContext app = new AnnotationConfigApplicationContext(BeanConfig.class);
        // Beanの名前を指定
        // Object型でBeanが返却されるため、キャストが必要
        return app.getBean(beanName);
    }

    public static <T> T getBeanFromConfig(Class<T> clazz, String beanName) {
        ApplicationContext app = new AnnotationConfigApplicationContext(BeanConfig.class);
        // Beanの型と名前を指定
        // 指定した型を持つBeanがDIコンテナに複数存在する場合に使用する
        return app.getBean(beanName, clazz);
    }

    /**
     * scan
     */
    public static <T> T getBeanFromComponentScan(Class<T> clazz) {
        // 指定したpackage名以下をコンポーネントスキャンする
        ApplicationContext app = new AnnotationConfigApplicationContext("com.example");
        return app.getBean(clazz);
    }

    /**
     * XMLから取得
     */
    public static <T> T getBeanFromXml(Class<T> clazz, String beanName) {
        ApplicationContext app = new ClassPathXmlApplicationContext("bean.xml");
        return app.getBean(beanName, clazz);
    }

    public static Object getBeanFromXml(String beanName) {
        ApplicationContext app = new ClassPathXmlApplicationContext("bean.xml");
        return app.getBean(beanName);
    }

    // この方法は非推奨
//    public static Object getBeanFromXml2(String beanName) {
//        Resource resource = new ClassPathResource("bean.xml");
//        // 定義ファイルを元にBeanFactoryをインスタンス化
//        BeanFactory factory = new XmlBeanFactory(resource);
//        return factory.getBean(beanName);
//    }
}
