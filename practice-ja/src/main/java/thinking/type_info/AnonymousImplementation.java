package thinking.type_info;
// Anonymous inner classes can't hide from reflection.
import thinking.type_info.interfacea.A;

import static net.mindview.util.Print.*;

/**
 * 知识点：
 *
 * 知识点概括：
 */
class AnonymousA { //@TkY-Doing
  public static A makeA() {
    return new A() { //A的匿名类
      public void f() { print("public C.f()"); }
      public void g() { print("public C.g()"); }
      void u() { print("package C.u()"); }
      protected void v() { print("protected C.v()"); }
      private void w() { print("private C.w()"); }
    };
  }
}	

public class AnonymousImplementation {

  public static void main(String[] args) throws Exception { //Doing
    A a = AnonymousA.makeA();
    a.f();
    System.out.println(a.getClass().getName());
    // Reflection still gets into the anonymous class:
    HiddenImplementation.callHiddenMethod(a, "g"); //通过反射方式调用匿名类中方法
    HiddenImplementation.callHiddenMethod(a, "u");
    HiddenImplementation.callHiddenMethod(a, "v");
    HiddenImplementation.callHiddenMethod(a, "w");

    /**
     * 输出结果：
     * thinking.type_info.AnonymousA$1
     * public C.g()
     * package C.u()
     * protected C.v()
     * private C.w()
     *
     * 结果分析：
     * 1）AnonymousA.makeA(); 通过匿名类的方式获取到A接口的实例
     *
     * 2）A接口中只有f()方法，而g()、u()等方法都是匿名类中新增的方法，
     *    把匿名类的实例赋值给A的引用后，通过反射也能调用到匿名类新增的方法
     */
  }
}
