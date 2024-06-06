package thinking.interface_relative.music5;

import thinking.polymorphism.music.Note;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/5
 */
public class Music5 {

    /**
     * 知识点（9.2）：接口
     */
    interface Instrument {
        int VALUE = 5;
        void play(Note n);
        void adjust();
    }

    static class Wind implements Instrument {
        public void play(Note n) {
           print(this + ".play() " + n);
        }
        public String toString() { return "Wind"; }
        public void adjust() { print(this + ".adjest()"); }
    }

    static class Percussion implements Instrument {
        public void play(Note n) {
            print(this + ".play() " + n);
        }

        public String toString() { return "Percussion"; }
        public void adjust() { print(this + ".adjust()"); }
    }

    static class Stringed implements Instrument {
        public void play(Note n) {
            print(this + ".play() " + n);
        }

        public String toString() { return "Stringed"; }
        public void adjust() { print(this + ".adjust()"); }
    }

    static class Brass extends Wind {
        public String toString() { return "Brass"; }
    }

    static class Woodwind extends Wind {
        public String toString() { return "Woodwind"; }
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
