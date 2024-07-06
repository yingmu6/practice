package thinking.type_info;
// Private inner classes can't hide from reflection.
import thinking.type_info.interfacea.A;

import static net.mindview.util.Print.*;

/**
 * 知识点（14.9）：通过反射访问类中成员
 *
 * 知识点概括：
 * 1）不管类中的成员的访问权限是怎样的，都可以通过反射机制来进行访问
 */
class InnerA { //@TkY-Done
  private static class C implements A {
    public void f() { print("public C.f()"); }
    public void g() { print("public C.g()"); }
    void u() { print("package C.u()"); }
    protected void v() { print("protected C.v()"); }
    private void w() { print("private C.w()"); }
  }
  public static A makeA() { return new C(); }
}	

public class InnerImplementation {

  public static void main(String[] args) throws Exception { //Done
    A a = InnerA.makeA();
    a.f(); //引用a指向的对象为：例如 InnerA$C@482
    System.out.println(a.getClass().getName());
    // Reflection still gets into the private class:（通过反射可以获取到类的私有信息）
    HiddenImplementation.callHiddenMethod(a, "g");
    HiddenImplementation.callHiddenMethod(a, "u");
    HiddenImplementation.callHiddenMethod(a, "v");
    HiddenImplementation.callHiddenMethod(a, "w");
  }

  /**
   * 输出结果：
   * public C.f()
   * thinking.type_info.InnerA$C
   * public C.g()
   * package C.u()
   * protected C.v()
   * private C.w()
   *
   * 结果分析：
   * 1）f()方法是C实现接口A的方法，其余的方法皆为C中自定义方法，通过反射机制，可以访问
   *   类中的方法，其本质是通过设置setAccessible(true)，来改变访问权限的。
   *
   * 2）调试时，可以看到a显示为"InnerA$C@482"，这其实显示的是基类Object的toString方法
   *   @后面的是 hashCode的值482，可以看到，只要对象信息不变，hashCode值就不改变。
   */
}