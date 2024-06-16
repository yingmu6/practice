package com.csy.spring.event;

import com.csy.spring.basic.Animal;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;

/**
 * @author chensy
 * @date 13/07/2022
 */
@Service
public class AnimalEventObj extends ApplicationEvent {

    private Animal animal;

    public AnimalEventObj(Animal animal) {
        super(animal);
        this.animal = animal;
    }

    public Animal getSource() {
        return animal;
    }
}
