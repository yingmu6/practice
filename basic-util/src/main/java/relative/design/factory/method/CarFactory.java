package relative.design.factory.method;

/**
 * @author : chensy
 * Date : 2020/11/17 下午2:44
 */
public class CarFactory implements ProductFactory {
    @Override
    public Product product() {
        return new Car();
    }
}
