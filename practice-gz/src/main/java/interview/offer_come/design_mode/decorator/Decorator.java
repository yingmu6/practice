package interview.offer_come.design_mode.decorator;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class Decorator implements Sourceable {

    private Sourceable source;

    public Decorator(Sourceable source) {
       super();
       this.source = source;
    }

    @Override
    public void createComputer() {
        source.createComputer();
        System.out.println("make system.");
    }
}
