package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class Games {

    interface Game {
        boolean move();
    }

    interface GameFactory {
        Game getGame();
    }

    static class Checkers implements Game {
        private Checkers() {}
        private int moves = 0;
        private static final int MOVES = 3;
        @Override
        public boolean move() {
            System.out.println("Checkers move " + moves);
            return ++moves != MOVES;
        }
        public static GameFactory factory = new GameFactory() {
            @Override
            public Game getGame() {
                return new Checkers();
            }
        };
    }

    static class Chess implements Game {
        private Chess() {}
        private int moves = 0;
        private static final int MOVES = 4;
        @Override
        public boolean move() {
            System.out.println("Chess move " + moves);
            return ++moves != MOVES;
        }

        public static GameFactory factory = new GameFactory() {
            @Override
            public Game getGame() {
                return new Chess();
            }
        };
    }

    public static void playGame(GameFactory factory) {
        Game s = factory.getGame();
        while (s.move())
            ;
    }

    /**
     * 使用匿名类访问
     */
    public static void main(String[] args) {
        playGame(Checkers.factory);
        playGame(Chess.factory);

        /**
         *
         * 输出结果：
         * Checkers move 0
         * Checkers move 1
         * Checkers move 2
         * Chess move 0
         * Chess move 1
         * Chess move 2
         * Chess move 3
         *
         * 结果分析：
         * 1）Checkers、Chess的构造方法都是私有的，static变量factory，可创建对应的实例对象。
         * 2）通过工厂GameFactory的getGame()产生不同的实例对象。然后再执行Game的move()方法。
         *
         * 总结概括：
         * 1）使用工厂接口的作为方法参数，工厂接口的实例用匿名类，在方法调用时，传入不同的工厂实例，就产生不同的行为，实现多态的效果
         *
         */
    }
}
