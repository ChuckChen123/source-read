package org.apache.dubbo.learn;

import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

@SPI("girl")
public interface Person {

    @Adaptive("adaptive")
    String sayHi();
}
