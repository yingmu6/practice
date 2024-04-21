package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class Apricot {

    void pick() {

    }

    void pit() {
        pick();
        // this.pick();

        /**
         * 说明：在pit()内部，可以写this.pick()，但是没有必要，编译器会自动帮你添加，只有
         * 当需要明确指出对当前对象的引用时，才需要使用this关键字。不需要在每个方法前都使用this调用，会使读程序的人不知所措，要尽量精简。
         */
    }
}
