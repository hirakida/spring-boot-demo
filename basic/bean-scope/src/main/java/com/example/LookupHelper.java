package com.example;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import com.example.SampleBean.PrototypeBean;

@Component
public class LookupHelper {

    // DIコンテナからBeanをLookupする
    // lookupメソッドは、privateやfinalを付けてはいけない、メソッドの引数も指定してはいけない
    @Lookup
    public PrototypeBean getPrototypeBean() {
        // 戻り値はダミーでOK
        return null;
    }
}
