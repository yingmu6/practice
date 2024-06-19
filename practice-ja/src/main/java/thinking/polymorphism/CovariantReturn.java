package thinking.polymorphism;

/**
 * @author orange
 * @date 2024/6/12
 */
public class CovariantReturn {

    /**
     * 知识点（8.4）：协变返回类型
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

    public static void main(String[] args) {
        Mill m = new Mill();
        Grain g = m.process();
        System.out.println(g);
        m = new WheatMill();
        g = m.process();
        System.out.println(g);
    }
}
