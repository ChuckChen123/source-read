package org.apache.dubbo.learn.test.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

@SPI("girl")
public interface Person {

    String sayHi();

    @Adaptive
    void testAdaptive(URL url);
}
