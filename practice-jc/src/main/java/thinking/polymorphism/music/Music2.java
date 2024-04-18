package thinking.polymorphism.music;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Music2 {

    /**
     * 知识点：忘记对象类型
     * 1）可通过重载方式，定义接收具体的子类参数，不用向上转型处理
     */
    static class Brass extends Instrument {
        public void play(Note n) {
            System.out.println("Brass.play() " + n);
        }
    }

    static class Stringed extends Instrument {
        public void play(Note n) {
            System.out.println("Stringed.play() " + n);
        }
    }

    public static void tune(Instrument i) { //从执行上看，重载的方法中，若指定了具体类型，不执行带有父类参数的方法
        i.play(Note.B_FLAT);
    }

    public static void tune(Wind i) {
        i.play(Note.MIDDLE_C);
    }

    public static void tune(Stringed i) { //重载方法
       i.play(Note.MIDDLE_C);
    }

    public static void tune(Brass i) {
        i.play(Note.MIDDLE_C);
    }

    public static void tuneWithParentArgs(Instrument i) {
        i.play(Note.B_FLAT);
    }

    public static void main(String[] args) {
        System.out.println("------使用具体子类类型作为参数------");
        Wind flute = new Wind();
        Stringed violin = new Stringed();
        Brass frenchHorn = new Brass();
        tune(flute);
        tune(violin);
        tune(frenchHorn);

        System.out.println("------使用父类类型作为参数------");
        tuneWithParentArgs(flute);
        tuneWithParentArgs(violin);
        tuneWithParentArgs(frenchHorn);

        /**
         * 输出结果：
         * ------使用具体子类类型作为参数------
         * Wind.play() MIDDLE_C
         * Stringed.play() MIDDLE_C
         * Brass.play() MIDDLE_C
         * ------使用父类类型作为参数------
         * Wind.play() B_FLAT
         * Stringed.play() B_FLAT
         * Brass.play() B_FLAT
         *
         * 结果分析：
         * 1）Wind、Stringed、Brass都是Instrument的子类，此处tune方法做了重载，直接定义了具体的子类类型，
         *   也就是没有通过向上转型方式（此时因为指定了具体子类，所以要对应重载方法，因为3个子类之间不能强制类型转换）
         *
         * 2）tuneWithParentArgs(Instrument) 定义了统一的接收的方法，即使用父类Instrument接收，做向上转型处理。
         */
    }
}
