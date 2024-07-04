package thinking.type_info;
import java.lang.reflect.*;

/**
 * 知识点（14.9）：接口与类型信息
 *
 * 知识点概括：
 * 1）
 *
 * 关联点学习：
 * 1）Filed父类AccessibleObject功能了解（Doing）
 *
 *
 */
class WithPrivateFinalField { //@TkY-Doing
  private int i = 1;
  private final String s = "I'm totally safe";
  private String s2 = "Am I safe?";
  public String toString() {
    return "i = " + i + ", " + s + ", " + s2;
  }
}

public class ModifyingPrivateFields {

  public static void main(String[] args) throws Exception { //Done
    WithPrivateFinalField pf = new WithPrivateFinalField();
    System.out.println(pf);
    Field f = pf.getClass().getDeclaredField("i");
    f.setAccessible(true); //设置字段为可访问
    System.out.println("f.getInt(pf): " + f.getInt(pf));
    f.setInt(pf, 47); //通过反射修改private字段值
    System.out.println(pf);
    f = pf.getClass().getDeclaredField("s");
    f.setAccessible(true);
    System.out.println("f.get(pf): " + f.get(pf));
    f.set(pf, "No, you're not!");
    System.out.println(pf);
    f = pf.getClass().getDeclaredField("s2");
    f.setAccessible(true);
    System.out.println("f.get(pf): " + f.get(pf));
    f.set(pf, "No, you're not!");
    System.out.println(pf);

    /**
     * 输出结果：
     * i = 1, I'm totally safe, Am I safe?
     * f.getInt(pf): 1
     * i = 47, I'm totally safe, Am I safe?
     * f.get(pf): I'm totally safe
     * i = 47, I'm totally safe, Am I safe?
     * f.get(pf): Am I safe?
     * i = 47, I'm totally safe, No, you're not!
     *
     * 结果分析：
     * 1）通过反射操作private字段时，先f.setAccessible(true);将字段设置为可访问，即可进行操作
     *    本质是设置java.lang.reflect.AccessibleObject#override字段的值
     */
  }
}
