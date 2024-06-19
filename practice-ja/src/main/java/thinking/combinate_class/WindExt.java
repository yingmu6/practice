package thinking.combinate_class;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class WindExt {

    /**
     * 知识点（7.7）：向上转型
     */
    static class Instrument {
        public void play() {}
        static void tune(Instrument i) {
           i.play();
        }
    }

    static public class Wind extends Instrument {
        public static void main(String[] args) {
           Wind flute = new Wind();
           Instrument.tune(flute);
        }
    }
}
