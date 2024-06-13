package thinking.generic_type;//: generics/SuperTypeWildcards.java
import java.util.*;

public class SuperTypeWildcards {

  /**
   * 知识点（15.10.2）：逆变
   */

  static void writeTo(List<? super Apple> apples) {
    apples.add(new Apple());
    apples.add(new Jonathan());
    // apples.add(new Fruit()); // Error
  }
} ///:~
