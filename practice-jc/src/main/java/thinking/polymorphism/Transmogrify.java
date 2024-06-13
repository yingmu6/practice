package thinking.polymorphism;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/12
 */
public class Transmogrify {

    /**
     * 知识点（8.5）：用继承进行设计
     */
    static class Actor {
        public void act() {}
    }

    static class HappyActor extends Actor {
        public void act() {
            print("HappyActor");
        }
    }

    static class SadActor extends Actor {
        public void act() {
            print("SadActor");
        }
    }

    static class State {
        private Actor actor = new HappyActor();
        public void change() {
            actor = new SadActor();
        }
        public void performPlay() {
            actor.act();
        }
    }

    public static void main(String[] args) {
        State state = new State();
        state.performPlay();
        state.change();
        state.performPlay();
    }
}
