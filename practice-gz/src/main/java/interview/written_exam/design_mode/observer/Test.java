package interview.written_exam.design_mode.observer;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class Test { //@MsY-Doing

    /**
     * 知识点：（观察者模式）
     *
     * 知识点概括：
     * 1）
     */

    public static void main(String[] args) {
        Whether whether = new Whether(); //创建主题
        WhetherDisplay1 d1 = new WhetherDisplay1(whether);
        WhetherDisplay2 d2 = new WhetherDisplay2(whether); //观察者主动注册到主题
        whether.setTemperature(27); //主题内容变更时，会遍历所有的观察者，把变更的内容通知到观察者
        whether.setTemperature(26);

        /**
         * 输出结果：
         * display1***:27.0
         * display2-----27.0
         * display1***:26.0
         * display2-----26.0
         *
         * 结果分析：
         * 1）主题会维护观察者引用的列表，创建观察者时，先将观察者注册到主题中，即将观察者引用加入到主题维护的引用列表中
         * 2）主题中在成员变量变更时，遍历所有观察者，将主题中的变更的值，传递给所有观察者。
         * 3）主要流程就是：观察者主动注册到主题的引用列表 -》主题中的内容变更时，遍历所有观察者，并将变更值传给观察者
         *    =》观察者收到变更的值，做各自的业务处理
         */
    }
}
