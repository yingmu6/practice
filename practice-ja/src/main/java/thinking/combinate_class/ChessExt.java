package thinking.combinate_class;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class ChessExt {

    /**
     * 知识点（7.2.1）：带参数的构造器
     */

    static class Game {
        Game(int i) {
            print("Game constructor");
        }
    }

    static class BoardGame extends Game {
        BoardGame(int i) {
            super(i);
            print("BoardGame constructor");
        }
    }

    static public class Chess extends BoardGame {
        Chess() {
            super(11);
            print("Chess constructor");
        }

        public static void main(String[] args) {
            Chess x = new Chess();
        }
    }
}
