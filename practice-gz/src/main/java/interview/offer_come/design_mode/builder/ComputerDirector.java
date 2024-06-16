package interview.offer_come.design_mode.builder;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class ComputerDirector {
    public Computer constructComputer(ComputerBuilder computerBuilder) {
        computerBuilder.buildMemory();
        computerBuilder.buildCpu();
        computerBuilder.buildDisk();
        return computerBuilder.buildComputer();
    }
}
