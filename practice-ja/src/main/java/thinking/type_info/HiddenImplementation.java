package thinking.type_info;
// Sneaking（秘密地） around package access.
import thinking.type_info.interfacea.A;
import thinking.type_info.packageaccess.HiddenC;

import java.lang.reflect.*;

public class HiddenImplementation { //@TkY-Done

  /**
   * 知识点：通过反射机制，访问不同包下类的成员
   */

  public static void main(String[] args) throws Exception { //Done
    A a = HiddenC.makeA();
    a.f();
    System.out.println(a.getClass().getName());
    // Compile error: cannot find symbol 'C':
//    if(a instanceof C) { //此处会编译错误：C直接访问不了，要在同一个包中
//      C c = (C)a;
//      c.g();
//    }
    // Oops! Reflection still allows us to call g():
    callHiddenMethod(a, "g");
    // And even methods that are less accessible!
    callHiddenMethod(a, "u");
    callHiddenMethod(a, "v");
    callHiddenMethod(a, "w");
    callHiddenMethod(a, "cc");

    /**
     * 输出结果：
     * thinking.type_info.packageaccess.C
     * public C.g()
     * package C.u()
     * protected C.v()
     * private C.w()
     * private C.cc()
     *
     * 结果分析：
     * 1）C是类HiddenC中的默认类，只有在同一个包中，才能直接访问。此处是把C的对象赋值给A
     *    然后再通过反射，来访问C中的所有方法。
     */
  }
  static void callHiddenMethod(Object a, String methodName) throws Exception {
    Method g = a.getClass().getDeclaredMethod(methodName);
    g.setAccessible(true); //置为可访问，也就是不管是private、protected等，都能访问
    g.invoke(a);
  }
}
