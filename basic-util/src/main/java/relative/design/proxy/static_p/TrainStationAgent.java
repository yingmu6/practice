package relative.design.proxy.static_p;

/**
 * 火车站代售点（静态代理：与目标对象实现相同的接口）
 * @author : chensy
 * Date : 2020/11/11 上午10:08
 */
public class TrainStationAgent implements ITicket {
    //使用时，将目前对象委托给代理对象处理，（使用了向上转型的方式，可实现多态）
    private ITicket ticket;

//    private TrainStation trainStation; //直接用目标对象也是可以的，但这样不利于扩展，实现不了多态。  所以用接口比较好
//
//    public TrainStationAgent(TrainStation trainStation) {
//        this.trainStation = trainStation;
//    }

    public TrainStationAgent(ITicket ticket) {
        this.ticket = ticket;
    }

    @Override
    public Integer getTicket(double price) {
        System.out.println("这是代售点买的票");
        return ticket.getTicket(price);
//        return trainStation.getTicket(price);
    }
}
