package spring.annotation;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chensy
 * @date 2022/5/12
 */
@Service
public class AnimalImpl implements IAnimal {

    @Resource
    private List<IAnimal> animals;

    @Override
    public void cry() {
        System.out.println("animals的数据：" + animals.size());

    }
}
