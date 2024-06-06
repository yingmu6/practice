package thinking.interface_relative.music4;

import thinking.polymorphism.music.Note;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/5
 */
public class Music4 {

    /**
     * 知识点（9.1）：抽象类和抽象方法
     */
    static abstract class Instrument {
        private int i;
        public abstract void play(Note n);
        public String what() { return "Instrument"; }
        public abstract void adjust();
    }

    static class Wind extends Instrument {
        public void play(Note n) {
            print("Wind.play() " + n);
        }
        public String what() { return "Wind"; }
        public void adjust() {}
    }

    static class Percussion extends Instrument {
        public void play(Note n) {
           print("Percussion.play() " + n);
        }
        public String what() { return "Percussion"; }
        public void adjust() {}
    }

    static class Stringed extends Instrument {
        public void play(Note n) {
            print("Stringed.play() " + n);
        }
        public String what() { return "Stringed"; }
        public void adjust() {}
    }

    static class Brass extends Wind {
        public void play(Note n) {
           print("Brass.play() " + n);
        }
    }

    static class Woodwind extends Wind {
        public void play(Note n) {
            print("Woodwind.play() " + n);
        }
        public String what() { return "Woodwind"; }
    }

    static void tune(Instrument i) {
       i.play(Note.MIDDLE_C);
    }

    static void tuneAll(Instrument[] e) {
        for (Instrument i : e) {
            tune(i);
        }
    }

    public static void main(String[] args) {
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
