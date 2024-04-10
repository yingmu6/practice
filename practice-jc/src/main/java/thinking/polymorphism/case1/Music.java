package thinking.polymorphism.case1;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class Music {

    /**
     * 知识点：向上转型
     * 1）向上转型：子类引用的对象转换为父类类型
     * 2）向下转型：父类引用的对象转换为子类类型
     * 3）向上转型的作用：让代码复用，提高代码的简洁性，可以实现多态
     */
    public static void tune(Instrument i) {
       i.play(Note.MIDDLE_C); //i的对象实例为：Wind@475，所以执行Wind中的play方法
    }

    public static void main(String[] args) {
        Wind flute = new Wind();
        tune(flute);

        /**
         * 输出结果：
         * Wind.play() MIDDLE_C
         *
         * 结果分析：
         * 1）Wind是Instrument的子类，执行tune(Instrument)方法时，将子类对象的引用赋值给父类，即完成了向上转型
         *   通过向上转型后，执行play(Note) 方法时，执行的是子类对象的行为。
         */
    }
}
