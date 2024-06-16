package interview.offer_come.design_mode.factory.abstract_factory;

/**
 * @author chensy
 * @date 2024/3/14
 */
public abstract class AbstractFactory {
    public abstract Phone createPhone(String brand);
    public abstract Computer createComputer(String brand);
}
