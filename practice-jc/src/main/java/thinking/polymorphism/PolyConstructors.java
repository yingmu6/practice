package thinking.polymorphism;

/**
 * @author chensy
 * @date 2024/4/18
 */
public class PolyConstructors { //@TkY-Doing

    /**
     * 知识点：
     *
     * 知识点概要：
     * 1）
     */

    static class Glyph {
        void draw() {
            System.out.println("Glyph.draw()");
        }
        Glyph() {
            System.out.println("Glyph() before draw()");
            draw(); //调用成员方法参与初始化（此处因为子类重写了draw()，所以调用子类draw()）
            System.out.println("Glyph() after draw()");
        }
    }

    static class RoundGlyph extends Glyph {
        private int radius = 1;

        private static int radiusV2 = 3;

        RoundGlyph(int r) {
            radius = r;
            System.out.println("RoundGlyph.RoundGlyph()，radius = " + radius);
        }

        void draw() {
            System.out.println("RoundGlyph.draw()，radius = " + radius + "，radiusV2 = " + radiusV2);
        }
    }

    public static void main(String[] args) {
        new RoundGlyph(5);

        /**
         * 输出结果：
         * Glyph() before draw()
         * RoundGlyph.draw()，radius = 0，radiusV2 = 3
         * Glyph() after draw()
         * RoundGlyph.RoundGlyph()，radius = 5
         *
         * 结果分析：
         * 1）执行new RoundGlyph(5); 会先执行父类Glyph构造方法。
         *
         * 2）Glyph构造方法中调用了draw()方法，而该方法被子类RoundGlyph实现，
         *    所以会调用子类的draw()方法，而此时子类还没有调用构造方法，也就是还没有初始化
         *    所以成员变量radius为0，但是加载类的时候静态变量就要值了，所以radiusV2为3
         */
    }
}
