package thinking.polymorphism.case3;

import thinking.polymorphism.case1.Note;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Woodwind extends Wind {
    void play(Note n) {
        System.out.println("Woodwind.play() " + n);
    }

    String what() {
        return "Woodwind";
    }
}
