package interview.offer_come.design_mode.builder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class ComputerConcreteBuilder implements ComputerBuilder {

    Computer computer;

    private final static Log logger = LogFactory.getLog(ComputerConcreteBuilder.class);

    public ComputerConcreteBuilder() {
        computer = new Computer();
    }

    @Override
    public void buildCpu() {
        logger.info("buildCpu......");
        computer.setCpu("8core");
    }

    @Override
    public void buildMemory() {
        logger.info("buildMemory......");
        computer.setMemory("16G");
    }

    @Override
    public void buildDisk() {
        logger.info("buildDisk......");
        computer.setDisk("1TG");
    }

    @Override
    public Computer buildComputer() {
        return computer;
    }
}
