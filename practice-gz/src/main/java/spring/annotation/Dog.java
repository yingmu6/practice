package spring.annotation;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author chensy
 * @date 2022/7/1
 */
@Service
public class Dog implements IAnimal {

    @Override
    public void cry() {
        System.out.println(new Random() + ":狗叫");
    }
}
