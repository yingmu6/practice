package thinking.polymorphism.case3;

import thinking.polymorphism.case1.Note;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Instrument {
    void play(Note n) {
        System.out.println("Instrument.play() " + n);
    }

    String what() {
        return "Instrument";
    }

    void adjust() {
        System.out.println("Adjusting Wind");
    }
}
