package thinking.polymorphism.case3;

import thinking.polymorphism.case1.Note;

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
