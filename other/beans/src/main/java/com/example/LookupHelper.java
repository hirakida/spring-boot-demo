package com.example;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.example.bean.ComponentBean;

/**
 * Lookup(DIコンテナからBeanを取得する) helper
 * ApplicationContextはSpring FrameworkでDIコンテナの役割を担う
 */
@Component
public class LookupHelper {

    /**
     * DIコンテナからBeanをLookupする
     * lookupメソッドは、privateやfinalを付けてはいけない、メソッドの引数も指定してはいけない
     */
    @Lookup
    public ComponentBean getComponentBean() {
        // 戻り値はダミーでOK
        return null;
    }

    /**
     * Java Configから取得
     */
    public <T> T getBeanFromConfig(Class<T> clazz) {
        // Java Config(Bean定義ファイル)を渡してDIコンテナを生成
        ApplicationContext app = new AnnotationConfigApplicationContext(BeanConfig.class);
        // Beanの型を指定して、DIコンテナからインスタンスを取得
        // 指定した型を持つBeanがDIコンテナに一つだけ存在する場合に使用する
        return app.getBean(clazz);
    }

    public Object getBeanFromConfig(String beanName) {
        ApplicationContext app = new AnnotationConfigApplicationContext(BeanConfig.class);
        // Beanの名前を指定
        // Object型でBeanが返却されるため、キャストが必要
        return app.getBean(beanName);
    }

    public <T> T getBeanFromConfig(Class<T> clazz, String beanName) {
        ApplicationContext app = new AnnotationConfigApplicationContext(BeanConfig.class);
        // Beanの型と名前を指定
        // 指定した型を持つBeanがDIコンテナに複数存在する場合に使用する
        return app.getBean(beanName, clazz);
    }

    /**
     * アノテーションベースConfigurationを使用する
     */
    public <T> T getBeanFromComponentScan(Class<T> clazz) {
        // 指定したpackage名以下をコンポーネントスキャンする
        ApplicationContext app = new AnnotationConfigApplicationContext("com.example");
        return app.getBean(clazz);
    }

    /**
     * XMLから取得
     */
    public <T> T getBeanFromXml(Class<T> clazz, String beanName) {
        ApplicationContext app = new ClassPathXmlApplicationContext("bean.xml");
        return app.getBean(beanName, clazz);
    }

    public Object getBeanFromXml(String beanName) {
        ApplicationContext app = new ClassPathXmlApplicationContext("bean.xml");
        return app.getBean(beanName);
    }

    // この方法は非推奨
//    public Object getBeanFromXml2(String beanName) {
//        Resource resource = new ClassPathResource("bean.xml");
//        // 定義ファイルを元にBeanFactoryをインスタンス化
//        BeanFactory factory = new XmlBeanFactory(resource);
//        return factory.getBean(beanName);
//    }
}
