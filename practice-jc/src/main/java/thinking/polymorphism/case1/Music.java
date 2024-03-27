package thinking.polymorphism.case1;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class Music {

    public static void tune(Instrument i) {
       i.play(Note.MIDDLE_C);
    }

    public static void main(String[] args) {
        Wind flute = new Wind();
        tune(flute);

        /**
         * （场景：向上转型）
         *
         * 输出结果：
         * Wind.play() MIDDLE_C
         *
         * 结果分析：
         *
         * 总结概括：
         */
    }
}
