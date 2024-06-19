package thinking.polymorphism.music3;

import thinking.polymorphism.music.Note;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Percussion extends Instrument {
    void play(Note n) {
        System.out.println("Percussion.play() " + n);
    }

    String what() {
        return "Percussion";
    }

    void adjust() {
        System.out.println("Adjusting Stringed");
    }
}
