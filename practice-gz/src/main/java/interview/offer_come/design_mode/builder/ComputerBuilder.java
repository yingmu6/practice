package interview.offer_come.design_mode.builder;

/**
 * @author chensy
 * @date 2024/3/14
 */
public interface ComputerBuilder {
    void buildCpu();

    void buildMemory();

    void buildDisk();

    Computer buildComputer();
}