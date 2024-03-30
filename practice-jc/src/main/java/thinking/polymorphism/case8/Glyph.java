package thinking.polymorphism.case8;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Glyph {
    void draw() {
        System.out.println("Glyph.draw()");
    }

    Glyph() {
        System.out.println("Glyph() before draw()");
        draw();
        System.out.println("Glyph() after draw()");
    }
}
