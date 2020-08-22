package relative.thread;

/**
 *
 * https://www.liaoxuefeng.com/wiki/1252599548343744/1306581251653666
 * @author : chensy
 * Date : 2020/7/28 上午11:31
 */
public class ThreadLocalTest {
    static ThreadLocal<Animal> threadLocal = new ThreadLocal<Animal>();

    public static void main(String[] args) {
        Animal animal = threadLocal.get();
        if (animal == null) {
            Animal temp = new Animal();
            temp.setWeight(11);
            threadLocal.set(temp); //设置值
        }
        System.out.println(threadLocal.get().getWeight());

        Thread thread1 = new Thread();
        use1();
        use2();
    }

    public static void use1() {
//        ThreadLocal<Animal> threadLocal = new ThreadLocal<Animal>();
//        System.out.println(threadLocal.get().getWeight()); //获取值  此处报控制正

//        ThreadGroup group = Thread.currentThread().getThreadGroup();
//        System.out.println(group.activeCount());

        System.out.println(threadLocal.get().getWeight());
    }

    public static void use2() {
        System.out.println(threadLocal.get().getWeight());
    }

    static class Animal {
        private double weight;
        public double getWeight() {
            return weight;
        }
        public void setWeight(double weight) {
            this.weight = weight;
        }
    }

    /**
     * ThreadLocal表示线程的“局部变量”，它确保每个线程的ThreadLocal变量都是各自独立的；(线程安全的，每个线程都有副本)
     * 可以在同一个线程中传递对象: ThreadLocal适合在一个线程的处理流程中保持上下文（避免了同一参数在所有方法中传递）
     * 使用ThreadLocal要用try ... finally结构，并在finally中清除。
     *
     * 理解ThreadLocal  https://zhuanlan.zhihu.com/p/61587053
     * 1）ThreadLocal的用途大致了解了，一是线程安全，二是可以在同一个线程中传递对象，不需要再每个方法里加上参数
     * 2）但是详情使用以及底层原理还需要进一步了解
     */
}
