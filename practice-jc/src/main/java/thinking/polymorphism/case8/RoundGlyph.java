package thinking.polymorphism.case8;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class RoundGlyph extends Glyph {

    private int radius = 1;

    RoundGlyph(int r) {
       radius = r;
       System.out.println("RoundGlyph.RoundGlyph()，radius");
    }

    void draw() {

    }
}
