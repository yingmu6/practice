package com.csy.xml.provider.spi;

import org.apache.dubbo.common.extension.SPI;

@SPI("dog")
public interface Animal {
    //    @Adaptive
    void cry();
}
