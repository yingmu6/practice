package relative.design.proxy.static_p;

import java.io.Serializable;

/**
 * 火车站
 * @author : chensy
 * Date : 2020/11/11 上午10:03
 */
public class TrainStation extends Test implements ITicket, Serializable, Cloneable { //实现Serializable, Cloneable，为了测试Class中的getInterfaces（）
    @Override
    public Integer getTicket(double price) { //买票
        return (int) (price / 50);
    }

    public static void main(String[] args) {
        System.out.println(ITicket.class.getInterfaces());
    }
}

class Test {

}
