package thinking.type_info.packageaccess;
import thinking.type_info.interfacea.A;

import static net.mindview.util.Print.*;

class C implements A {
  public void f() { print("public C.f()"); }
  public void g() { print("public C.g()"); }
  void u() { print("package C.u()"); }
  protected void v() { print("protected C.v()"); }
  private void w() { print("private C.w()"); }
  private void cc() { print("private C.cc()"); }
}

public class HiddenC {
  public static A makeA() { return new C(); }
} ///:~
