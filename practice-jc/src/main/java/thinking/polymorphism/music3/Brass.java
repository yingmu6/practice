package thinking.polymorphism.music3;

import thinking.polymorphism.music.Note;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Brass extends Wind {
    void play(Note n) {
        System.out.println("Brass.play() " + n);
    }

    void adjust() {
        System.out.println("Adjusting Brass");
    }
}
