package org.apache.dubbo.learn.test.spi;

import org.apache.dubbo.common.URL;

public class Girl implements Person {
    @Override
    public String sayHi() {
        return "我是韩梅梅";
    }

    @Override
    public void testAdaptive(URL url) {
        System.out.println("Girl");
    }
}
