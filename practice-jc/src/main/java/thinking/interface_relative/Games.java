package thinking.interface_relative;

import static net.mindview.util.Print.print;

/**
 *
 * @author orange
 * @date 2024/6/6
 */
public class Games {

    /**
     * 知识点（9.9）：接口与工厂
     */

    interface Game { boolean move(); }
    interface GameFactory { Game getGame(); }

    static class Checkers implements Game {
        private int moves = 0;
        private static final int MOVES = 3;
        public boolean move() {
            print("Checkers move " + moves);
            return ++moves != MOVES;
        }
    }

    static class CheckersFactory implements GameFactory {
        public Game getGame() { return new Checkers(); }
    }

    static class Chess implements Game {
        private int moves = 0;
        private static final int MOVES = 4;
        public boolean move() {
            print("Chess move " + moves);
            return ++moves != MOVES;
        }
    }

    static class ChessFactory implements GameFactory {
        public Game getGame() { return new Chess(); }
    }

    public static void playGame(GameFactory factory) {
        Game s = factory.getGame();
        while (s.move())
            ;
    }

    public static void main(String[] args) {
        playGame(new CheckersFactory());
        playGame(new ChessFactory());
    }

}
