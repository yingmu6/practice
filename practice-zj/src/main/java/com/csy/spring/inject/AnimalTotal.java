package com.csy.spring.inject;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author chensy
 * @date 29/08/2022
 */
@Component
public class AnimalTotal {
    @Resource
    private Set<IAnimal> animalSet;

    public AnimalTotal() {
    }

    public Set<IAnimal> getAnimalSet() {
        return animalSet;
    }

}
