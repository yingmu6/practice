package interview.offer_come.design_mode.builder;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class ComputerConcreteBuilder implements ComputerBuilder {

    Computer computer;

    public ComputerConcreteBuilder() {
        computer = new Computer();
    }

    @Override
    public void buildCpu() {
        System.out.println("buildCpu......");
        computer.setCpu("8core");
    }

    @Override
    public void buildMemory() {
        System.out.println("buildMemory......");
        computer.setMemory("16G");
    }

    @Override
    public void buildDisk() {
        System.out.println("buildDisk......");
        computer.setDisk("1TG");
    }

    @Override
    public Computer buildComputer() {
        return computer;
    }
}
