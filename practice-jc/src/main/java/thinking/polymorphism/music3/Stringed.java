package thinking.polymorphism.music3;

import thinking.polymorphism.music.Note;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Stringed extends Instrument {

    void play(Note n) {
        System.out.println("Stringed.play() " + n);
    }

    String what() {
        return "Stringed";
    }

    void adjust() {
        System.out.println("Adjusting Stringed");
    }
}
