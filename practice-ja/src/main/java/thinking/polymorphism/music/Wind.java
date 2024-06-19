package thinking.polymorphism.music;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class Wind extends Instrument {
    public void play(Note n) {
        System.out.println("Wind.play() " + n);
    }
}
