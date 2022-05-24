package spring.aspectj;

import org.springframework.stereotype.Component;
import spring.inter.IAnimal;

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
