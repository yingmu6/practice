package thinking.type_info;
// Private inner classes can't hide from reflection.
import thinking.type_info.interfacea.A;

import static net.mindview.util.Print.*;

/**
 * 知识点（14.9）：接口与类型信息
 */
class InnerA {
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

  public static void main(String[] args) throws Exception { //Doing_@pause-07/05
    A a = InnerA.makeA();
    a.f(); //引用a执行的对象为：例如 InnerA$C@482
    System.out.println(a.getClass().getName());
    // Reflection still gets into the private class:
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
   *
   *
   */
}