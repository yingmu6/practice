package thinking.generic_type.coffee;

import net.mindview.util.Generator;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author chensy
 * @date 2024/5/17
 */
public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {
    @Override
    public Coffee next() {
        return null;
    }

    @Override
    public Iterator<Coffee> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Coffee> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Coffee> spliterator() {
        return Iterable.super.spliterator();
    }
}
