package thinking.inner_class.case2;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class ZdTest {

    /**
     * 场景1：使用匿名类访问
     */
    @Test
    public void basicUse() {
        Games.playGame(Checkers.factory);
        Games.playGame((Chess.factory));

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
