package thinking.type_info;//: typeinfo/InterfaceViolation.java
// Sneaking around an interface.

import thinking.type_info.interfacea.A;

/**
 * 知识点（14.9）：接口与类型信息
 */
class B implements A {
  public void f() {}
  public void g() {}
}

public class InterfaceViolation {
  public static void main(String[] args) {
    A a = new B();
    a.f();
    // a.g(); // Compile error
    System.out.println(a.getClass().getName());
    if(a instanceof B) {
      B b = (B)a;
      b.g();
    }
  }
} /* Output:
B
*///:~
