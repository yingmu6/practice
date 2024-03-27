package thinking.inner_class.case2;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class Games {
    public static void playGame(GameFactory factory) {
        Game s = factory.getGame();
        while (s.move())
            ;
    }

    public static void main(String[] args) {
        playGame(Checkers.factory);
        playGame((Chess.factory));

        /**
         * （场景：使用匿名类访问）
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
         *
         * 总结概括：
         */
    }
}
