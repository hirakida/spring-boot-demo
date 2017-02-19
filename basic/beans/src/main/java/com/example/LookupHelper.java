package com.example;

import org.springframework.beans.factory.annotation.Lookup;
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
}
