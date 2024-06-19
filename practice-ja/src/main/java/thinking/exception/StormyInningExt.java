package thinking.exception;

/**
 * @author orange
 * @date 2024/6/4
 */
public class StormyInningExt {

    static class BaseballException extends Exception {}
    static class Foul extends BaseballException {}
    static class Strike extends BaseballException {}

//    abstract class Inning {
//        public Inning() throws BaseballException {}
//        public void event() throws BaseballException {
//
//        }
//        public abstract void atBat() throws Strike, Foul;
//        public void walk() {}
//    }

    static class StormException extends Exception {}
    static class RainedOut extends StormException {}
    static class PopFoul extends Foul {}

    interface Storm {
        public void event() throws RainedOut;
        public void rainHard() throws RainedOut;
    }


}
