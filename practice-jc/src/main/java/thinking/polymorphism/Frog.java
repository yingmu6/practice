package thinking.polymorphism;

/**
 * @author chensy
 * @date 2024/4/18
 */
public class Frog extends FrogExt.Amphibian { //@TkY-Doing

    /**
     * 知识点：继承与清理
     *
     * 知识点概括：
     * 1）
     */
    private FrogExt.Characteristic p = new FrogExt.Characteristic("Croaks");
    private FrogExt.Description t = new FrogExt.Description("Eats Bugs"); //先进行成员变量初始化，在调用构造方法

    public Frog() {
        System.out.println("Frog()");
    }

    @Override
    protected void dispose() {
        System.out.println("Frog dispose");
        t.dispose();
        p.dispose();
        super.dispose();
    }

    public static void main(String[] args) {
        Frog frog = new Frog(); //初始化时：从父类开始，从上到下初始化
        System.out.println("Bye!");
        frog.dispose();         //销毁时：从子类开始，通过super调用，实现从下到上销毁

        /**
         * 输出结果：
         * Creating Characteristic is alive
         * Creating Description Basic Living Creature
         * LivingCreature()
         * Creating Characteristic has heart
         * Creating Description Animal not Vegetable
         * Animal()
         * Creating Characteristic can live in water
         * Creating Description Both water and land
         * Amphibian()
         * Creating Characteristic Croaks
         * Creating Description Eats Bugs
         * Frog()
         * Bye!
         * Frog dispose
         * disposing Description Eats Bugs
         * disposing Characteristic Croaks
         * Amphibian dispose
         * disposing Description Both water and land
         * disposing Characteristic can live in water
         * Animal dispose
         * disposing Description Animal not Vegetable
         * disposing Characteristic has heart
         * LivingCreature dispose
         * disposing Description Basic Living Creature
         * disposing Characteristic is alive
         *
         * 结果分析：
         * 1）继承关系：Frog -> Amphibian -> Animal -> LivingCreature
         *
         * 2）在new Frog()初始化时，会最先执行最顶层父类LivingCreature的初始化，
         *    再依次执行其子类的初始化
         *
         * 3）在执行frog.dispose()销毁时，会先调用子类Frog方法，然后在方法中依次
         *    通过super调用父类的方式，来实现从子类到父类的调用
         */
    }
}
