package thinking.type_info;
// Using a dynamic proxy to create a Null Object.
import java.lang.reflect.*;
import java.util.*;
import net.mindview.util.*;

/**
 * 知识点（14.8）：空对象
 *
 * 知识点概括：
 * 1）
 *
 * 关联点学习：
 * 1）数组的初始化，即{}中使用构造方法、普通方法来构造（Doing）
 * 2）JDK动态代理的Proxy、InvocationHandler具体使用（Doing）
 *
 */
class NullRobotProxyHandler implements InvocationHandler { //@TkY-Doing
  private String nullName;
  private Robot proxied = new NRobot();
  NullRobotProxyHandler(Class<? extends Robot> type) {
    nullName = type.getSimpleName() + " NullRobot";
  }
  private class NRobot implements Null, Robot {
    public String name() { return nullName; }
    public String model() { return nullName; }
    public List<Operation> operations() {
      return Collections.emptyList();
    }
  }	
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    return method.invoke(proxied, args); //代理对象中的方法被调用时，会进行回调
  }
}

public class NullRobot {
  public static Robot
  newNullRobot(Class<? extends Robot> type) {
    return (Robot)Proxy.newProxyInstance( //使用jdk动态代理，创建代理对象
      NullRobot.class.getClassLoader(),
      new Class[]{ Null.class, Robot.class },
      new NullRobotProxyHandler(type)); //指定代理对象的调用处理器InvocationHandler
  }	
  public static void main(String[] args) { //Doing
    Robot[] bots = {
      new SnowRemovalRobot("SnowBee"),
      newNullRobot(SnowRemovalRobot.class) //创建代理对象
    };
    for(Robot bot : bots)
      Robot.Test.test(bot);

    /**
     * 输出结果：
     * Robot name: SnowBee
     * Robot model: SnowBot Series 11
     * SnowBee can shovel snow
     * SnowBee shoveling snow
     * SnowBee can chip ice
     * SnowBee chipping ice
     * SnowBee can clear the roof
     * SnowBee clearing roof
     * [Null Robot]
     * Robot name: SnowRemovalRobot NullRobot
     * Robot model: SnowRemovalRobot NullRobot
     *
     * 结果分析：
     * 1）通过Proxy.newProxyInstance(...)创建的代理对象，在对象的方法被调用时，
     *    会回调InvocationHandler中的invoke方法，可把具体的逻辑放入其中。
     *
     */
  }
}
