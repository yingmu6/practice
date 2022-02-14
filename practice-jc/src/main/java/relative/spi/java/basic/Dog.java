package relative.spi.java.basic;

/**
 * @author : chensy
 * Date : 2020-03-12 14:15
 */
public class Dog implements Animal {
    @Override
    public void cry() {
        System.out.println("狗叫！");
    }
}
