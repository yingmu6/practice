package spring.annotation;

import org.springframework.stereotype.Service;

/**
 * @author chensy
 * @date 2022/5/12
 */
@Service
public class AnimalImpl implements IAnimal {
    @Override
    public void cry() {
        System.out.println("animal 叫");
    }
}
