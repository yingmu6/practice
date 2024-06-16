package com.csy.spring.aspectj;

import com.csy.spring.inter.IAnimal;
import org.springframework.stereotype.Component;

/**
 * @author chensy
 * @date 2022/5/23
 */
@Component
public class Pig implements IAnimal {

    @Override
    public void cry() {
        System.out.println("pig 叫");
    }
}
