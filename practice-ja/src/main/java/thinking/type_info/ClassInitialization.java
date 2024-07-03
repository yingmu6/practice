package thinking.type_info;

/**
 * @author orange
 * @date 2024/6/12
 */

import java.util.Random;

/**
 * 知识点（14.2.1）：类字面常量
 *
 * 知识点概括：
 * 1）
 */

class Initable { //@TkY-Doing
    static final int staticFinal = 47;
    static final int staticFinal2 =
            ClassInitialization.rand.nextInt(1000);
    static {
        System.out.println("Initializing Initable");
    }
}

class Initable2 {
    static int staticNonFinal = 147;
    static {
        System.out.println("Initializing Initable2");
    }
}

class Initable3 {
    static int staticNonFinal = 74;
    static {
        System.out.println("Initializing Initable3");
    }
}

public class ClassInitialization {
    public static Random rand = new Random(47);
    public static void main(String[] args) throws ClassNotFoundException { //@Doing
        System.out.println("======P1======");
        Class initable = Initable.class;
        System.out.println("======P2======");
        System.out.println("After creating Initable ref");
        System.out.println(Initable.staticFinal);
        System.out.println(Initable.staticFinal2);
        System.out.println(Initable2.staticNonFinal);

        System.out.println("=======P3=====");
        Class initable3 = Class.forName("thinking.type_info.Initable3");
        System.out.println("=======P4=====");
        System.out.println("After creating Initable3 ref");
        System.out.println(Initable3.staticNonFinal);

        /**
         * 输出结果：
         * ======P1======
         * ======P2======
         * After creating Initable ref
         * 47
         * Initializing Initable
         * 258
         * Initializing Initable2
         * 147
         * =======P3=====
         * Initializing Initable3
         * =======P4=====
         * After creating Initable3 ref
         * 74
         *
         * 结果分析：
         * 1）P1和P2之间没有输出代码，说明Initable.class方式不会初始化static块
         *   P3和P4之间输出了Initable3中static块内容，说明Class.forName(...)方式会初始化对应类的静态块
         *
         * 问题点答疑：
         * 1）static常量和static块之间执行的前后位置有约定吗？
         */
    }
}
