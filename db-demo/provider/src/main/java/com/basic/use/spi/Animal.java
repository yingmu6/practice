package com.basic.use.spi;

import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

@SPI("dog")
public interface Animal {
//    @Adaptive
    void cry();
}
