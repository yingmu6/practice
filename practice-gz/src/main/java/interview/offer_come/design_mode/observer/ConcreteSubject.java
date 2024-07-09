package interview.offer_come.design_mode.observer;

/**
 * @author chensy
 * @date 2024/3/15
 */
public class ConcreteSubject extends Subject { //具体主题，@MsY-Doing

    /**
     * 知识点：观察者模式
     *
     * 知识点概括：
     * 1）
     *
     * 问题点答疑：
     * 1）观察者模式对应哪些常用的应用场景？
     *
     */
    @Override
    public void notifyObserver(String message) {
        for(Object obs : observerList) {
            System.out.println("notify observer " + message + " change...");
            ((Observer)obs).dataChange(message);
        }
    }

    public static void main(String[] args) { //Done
        Subject subject = new ConcreteSubject(); //主题
        Observer obs = new ConcreteObserver(); //观察者
        subject.add(obs); //将观察者添加到主题维护的列表中
        subject.notifyObserver("data1");

        /**
         * 输出结果：
         * notify observer data1change...
         * receive message:data1
         *
         * 结果分析：
         * 1）主题持有观察者列表，需要被通知的观察者要先主动添加到主题中
         *    当主题维护的数据有变化时，会依次将变更的数据通知给观察者
         *
         */
    }
}
