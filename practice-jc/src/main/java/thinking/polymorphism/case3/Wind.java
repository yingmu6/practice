package thinking.polymorphism.case3;

import thinking.polymorphism.case1.Note;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Wind extends Instrument {
    void play(Note n) {
        System.out.println("Wind.play() " + n);
    }

    String what() {
        return "Wind";
    }

    void adjust() {
        System.out.println("Adjusting Wind");
    }
}
