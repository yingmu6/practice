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
}
