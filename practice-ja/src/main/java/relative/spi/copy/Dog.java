package relative.spi.copy;

/**
 * @author : chensy
 * Date : 2020-03-12 14:39
 */
public class Dog implements IShout {
    @Override
    public void shout() {
        System.out.println("wang wang");
    }
}
