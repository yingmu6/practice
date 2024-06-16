package com.csy.spring.inject;

import org.springframework.stereotype.Component;

/**
 * @author chensy
 * @date 29/08/2022
 */
@Component
public class DogAnimal implements IAnimal {

    private String name = "dog";

    @Override
    public void cry() {
        System.out.println("狗叫");
    }
}
