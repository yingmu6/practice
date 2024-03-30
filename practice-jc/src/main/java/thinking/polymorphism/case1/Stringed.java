package thinking.polymorphism.case1;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Stringed extends Instrument {
    public void play(Note n) {
        System.out.println("Stringed.play() " + n);
    }
}
