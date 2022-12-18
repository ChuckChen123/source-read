package org.apache.dubbo.learn;

import org.apache.dubbo.common.extension.SPI;

@SPI("girl")
public interface Person {

    String sayHi();
}
