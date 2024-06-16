package interview.offer_come.design_mode.builder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class Test {

    private final static Log logger = LogFactory.getLog(Test.class);

    public static void main(String[] args) {
        ComputerDirector computerDirector = new ComputerDirector();
        ComputerBuilder computerConcreteBuilder = new ComputerConcreteBuilder();
        Computer computer = computerDirector.constructComputer(computerConcreteBuilder);

        logger.info(computer.getCpu());
        logger.info(computer.getDisk());
        logger.info(computer.getMemory());

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         * 问题点答疑：
         * 1）log日志为什么没有打印出来？
         */
    }
}
