package interview.offer_come.design_mode.builder;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class Test { //@MsY-Doing

    /**
     * 知识点：桥接器模式
     *
     * 知识点概括：
     * 1）
     */
    public static void main(String[] args) {
        ComputerDirector computerDirector = new ComputerDirector();
        ComputerBuilder computerConcreteBuilder = new ComputerConcreteBuilder();
        Computer computer = computerDirector.constructComputer(computerConcreteBuilder);

        System.out.println(computer.getCpu());
        System.out.println(computer.getDisk());
        System.out.println(computer.getMemory());

        /**
         * 输出结果：
         * buildMemory......
         * buildCpu......
         * buildDisk......
         * 8core
         * 1TG
         * 16G
         *
         * 结果分析：
         *
         * 问题点答疑：
         * 1）建造者模式，主要是替换set/get功能吗？
         *
         */
    }
}
