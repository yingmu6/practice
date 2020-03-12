package relative.spi.copy;

/**
 * @author : chensy
 * Date : 2020-03-12 14:39
 */
public class Cat implements IShout {
    @Override
    public void shout() {
        System.out.println("miao miao");
    }
}