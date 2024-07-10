package thinking.generic_type.decorator;
import java.util.*;

/**
 * 知识点（15.15.3）：使用装饰器模式
 *
 * 关联点学习：
 * 1）装饰器模式了解&学习（Doing）
 *
 * 问题点答疑：
 * 1）此用例是怎么体现出装饰器模式的？
 */
class Basic {
  private String value;
  public void set(String val) { value = val; }
  public String get() { return value; }
}

class Decorator extends Basic {
  protected Basic basic;
  public Decorator(Basic basic) { this.basic = basic; }
  public void set(String val) { basic.set(val); }
  public String get() { return basic.get(); }
}	

class TimeStamped extends Decorator {
  private final long timeStamp;
  public TimeStamped(Basic basic) {
    super(basic);
    timeStamp = new Date().getTime();
  }
  public long getStamp() { return timeStamp; }
}

class SerialNumbered extends Decorator {
  private static long counter = 1;
  private final long serialNumber = counter++;
  public SerialNumbered(Basic basic) { super(basic); }
  public long getSerialNumber() { return serialNumber; }
}	

public class Decoration {

  public static void main(String[] args) { //Doing
    TimeStamped t = new TimeStamped(new Basic());
    TimeStamped t2 = new TimeStamped(
      new SerialNumbered(new Basic())); //SerialNumbered继承Decorator，Decorator继承Basic
    // ! t2.getSerialNumber(); // Not available（未找到可利用的方法）
    SerialNumbered s = new SerialNumbered(new Basic());
    SerialNumbered s2 = new SerialNumbered(
      new TimeStamped(new Basic()));
    //! s2.getStamp(); // Not available（未找到可利用的方法）

    /**
     * 输出结果：
     * （输出空白行）
     *
     * 结果分析：
     * 1）
     */

  }
}
