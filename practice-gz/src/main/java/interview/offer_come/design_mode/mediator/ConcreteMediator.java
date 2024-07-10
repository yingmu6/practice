package interview.offer_come.design_mode.mediator;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class ConcreteMediator extends Mediator { //MsY-Doing

    /**
     * 知识点：中介者模式
     *
     * 知识点概括：
     * 1）
     *
     */

    public ConcreteMediator(Colleague colleagueTenant, Colleague colleagueLandlord) {
        super(colleagueTenant, colleagueLandlord);
    }

    @Override
    public boolean notifyColleagueTenant(String message) {
        if (colleagueTenant != null) {
            return colleagueTenant.operation(message);
        }
        return false;
    }

    @Override
    public boolean notifyColleagueLandlord(String message) {
        if (colleagueLandlord != null) {
            return colleagueLandlord.operation(message);
        }
        return false;
    }

    public static void main(String[] args) { //Done
        Colleague colleagueTenant = new ColleagueTenant();
        Colleague colleagueLandlord = new ColleagueLandlord();
        ConcreteMediator concreteMediator = new ConcreteMediator(colleagueTenant, colleagueLandlord);
        boolean result = concreteMediator.notifyColleagueTenant("想租大点面积的房子吗？"); //与租客沟通
        if (result) {
            concreteMediator.notifyColleagueLandlord("租客对面积满意"); //与房东沟通
        } else {
            concreteMediator.notifyColleagueLandlord("租客对面积不满意");
        }

        /**
         * 输出结果：
         * tenant receive a message from mediator:想租大点面积的房子吗？
         * landlord receive a message from mediator:租客对面积满意
         *
         * 结果分析：
         * 1）中介Mediator持有租客、房东的引用，可以向租客和房东单独发送消息，而租客和房东
         *    之间不用单独发送消息
         *
         * 2）创建租客、房东的引用后，就传给Mediator存储起来，然后由Mediator作为中介者
         *    来执行租客、房东之间操作和消息传递
         *
         * 3）租客、房东定义了接收消息的方法operation(String msg)，接收来自中介者的消息
         *
         */
    }
}
