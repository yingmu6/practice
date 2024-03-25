package com.csy.interview.offer_come.basic.annotation;

import lombok.Getter;
import lombok.Setter;

/**
 * @author chensy
 * @date 2024/3/16
 */

@Getter
@Setter
public class Apple {

    @FruitProvider(id = 1, name = "红富士", address = "陕西")
    private String appleProvider;
}
