package spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;
import spring.basic.Animal;

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
