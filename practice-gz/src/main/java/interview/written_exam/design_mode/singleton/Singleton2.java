package interview.written_exam.design_mode.singleton;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class Singleton2 { //@MsY-Doing

    /**
     * 知识点：单例模式之饿汉模式
     *
     * 知识点概括：
     * 1）在类加载的时候直接初始化静态变量
     * （不同于懒汉模式的点：懒汉模式是使用时才创建对象实例）
     */
    private static Singleton2 instance = new Singleton2();

    private Singleton2() {}

    public static synchronized Singleton2 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Singleton1 o1 = Singleton1.getInstance();
        Singleton1 o2 = Singleton1.getInstance();
        System.out.println("o1 = " + o1);
        System.out.println("o2 = " + o2);
        System.out.println("(o1 == o2) is " + (o1 == o2));

        /**
         * 输出结果：
         * o1 = interview.written_exam.design_mode.singleton.Singleton1@5680a178
         * o2 = interview.written_exam.design_mode.singleton.Singleton1@5680a178
         * (o1 == o2) is true
         *
         * 结果分析：
         * 1）在类加载时，就会初始化static成员变量，也就是会创建对象实例了
         */
    }
}
