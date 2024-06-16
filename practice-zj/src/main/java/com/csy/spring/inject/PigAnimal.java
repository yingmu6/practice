package com.csy.spring.inject;

import org.springframework.stereotype.Component;

/**
 * @author chensy
 * @date 29/08/2022
 */
@Component
public class PigAnimal implements IAnimal {

    private String name = "pig";

    @Override
    public void cry() {
        System.out.println("猪叫！");
    }
}
