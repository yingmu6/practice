package relative.design.proxy.cglib;

/**
 * @author : chensy
 * Date : 2020/11/11 下午12:11
 */
public class TicketBuy {
    public int getTicket(double price) {
        return (int) (price / 50);
    }
}
