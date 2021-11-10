package relative.thread.local;

/**
 * https://www.liaoxuefeng.com/wiki/1252599548343744/1306581251653666
 *
 * @author : chensy
 * Date : 2020/7/28 上午11:31
 */
public class ThreadLocalTest {
    static ThreadLocal<Animal> threadLocal = new ThreadLocal<Animal>();

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    AnimalClass animalClass = getTheadLocal();
                    int age = animalClass.getAge() + 1;
                    animalClass.setAge(age);
                    setTheadLocal(animalClass);
                    System.out.println(getTheadLocal());            // ThreadLocal：每个线程有个副本
                    System.out.println(AnimalContextHolder.TEST++); // 静态变量：线程间共享
                }
            }).start();
        }

    }

    // 设置上下文信息
    public static void setTheadLocal(AnimalClass animalClass) {
        AnimalContextHolder.setContext(animalClass);
    }

    // 获取上下文信息
    public static AnimalClass getTheadLocal() {
        return AnimalContextHolder.getContext();
    }

    public static void basic() {
        Animal animal = threadLocal.get();
        if (animal == null) {
            Animal temp = new Animal();
            temp.setWeight(12.22);
            threadLocal.set(temp); //设置值
        }
//        System.out.println(threadLocal.get().getWeight());

        /**
         * ThreadLocal线程的局部变量，各个线程维护的值互不干扰，
         * 同一个线程中，可以通过ThreadLocal在不同方法中传递对象的值
         */
        new Thread(() -> { //
            Animal temp = new Animal();
            temp.setWeight(11.11);
            threadLocal.set(temp);
            use1();
            use2();
        }).start();
//        Thread.currentThread().join();
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
     *
     * 实际上，可以把ThreadLocal看成一个全局Map<Thread, Object>：每个线程获取ThreadLocal变量时，总是使用Thread自身作为key：
     * Object threadLocalValue = threadLocalMap.get(Thread.currentThread());
     * 因此，ThreadLocal相当于给每个线程都开辟了一个独立的存储空间，各个线程的ThreadLocal关联的实例互不干扰。
     * 最后，特别注意ThreadLocal一定要在finally中清除
     *
     * Thread Join()的用法  https://www.cnblogs.com/duanxz/p/5038471.html
     */
}
