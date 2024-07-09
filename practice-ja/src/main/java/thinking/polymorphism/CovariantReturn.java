package thinking.polymorphism;

/**
 * @author orange
 * @date 2024/6/12
 */
public class CovariantReturn { //@TkY-Doing

    /**
     * 知识点（8.4）：协变返回类型
     *
     * 问题点答疑：
     * 1）什么叫作协变返回类型？
     */
    static class Grain {
        public String toString() { return "Grain"; }
    }

    static class Wheat extends Grain {
        public String toString() { return "Wheat";}
    }

    static class Mill {
        Grain process() { return new Grain(); }
    }

    static class WheatMill extends Mill {
        Wheat proce() { return new Wheat(); }
    }

    public static void main(String[] args) { //Done
        Mill m = new Mill();
        Grain g = m.process();
        System.out.println(g);
        m = new WheatMill();
        g = m.process();
        System.out.println(g);

        /**
         * 输出结果：
         * Grain
         * Grain
         *
         * 结果分析：
         * 1）Mill中的process()方法会创建并返回Grain实例
         *
         * 2）WheatMill继承了Mill类，Mill类中的process()
         *   方法也会创建并返回Grain实例
         */
    }
}
