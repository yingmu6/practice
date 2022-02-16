package com.basic.use.spi;

import org.apache.dubbo.common.extension.SPI;

@SPI("dog")
public interface Animal {
    //    @Adaptive
    void cry();
}
