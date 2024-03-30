package thinking.polymorphism.case3;

import thinking.polymorphism.case1.Note;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class Music3 {

    public static void tune(Instrument i) {
       i.play(Note.MIDDLE_C);
    }

    public static void tuneAll(Instrument[] e) {
       for (Instrument i : e) {
          tune(i);
       }
    }

    public static void main(String[] args) { //可扩展性
        Instrument[] orchestra = {
                new Wind(),
                new Percussion(),
                new Stringed(),
                new Brass(),
                new Woodwind()
        };
        tuneAll(orchestra);
    }
}
