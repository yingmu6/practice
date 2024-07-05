//: typeinfo/pets/LiteralPetCreator.java
// Using class literals.
package thinking.type_info.pets;
import java.util.*;

public class LiteralPetCreator extends PetCreator { //@TkY-Doing

  /**
   * 知识点（14.3.1）：使用类字面常量
   */

  // No try block needed.
  @SuppressWarnings("unchecked")
  public static final List<Class<? extends Pet>> allTypes =
    Collections.unmodifiableList(Arrays.asList(
      Pet.class, Dog.class, Cat.class,  Rodent.class,
      Mutt.class, Pug.class, EgyptianMau.class, Manx.class,
      Cymric.class, Rat.class, Mouse.class,Hamster.class));
  // Types for random creation:
  private static final List<Class<? extends Pet>> types =
    allTypes.subList(allTypes.indexOf(Mutt.class),
      allTypes.size()); //subList(int fromIndex, int toIndex)子列表，区间为[fromIndex, toIndex)
  public List<Class<? extends Pet>> types() {
    return types;
  }	
  public static void main(String[] args) { //Done
    System.out.println(types);

    /**
     * 输出结果：
     * Rat Manx Cymric Mutt Pug Cymric Pug Manx Cymric Rat EgyptianMau Hamster EgyptianMau Mutt Mutt Cymric Mouse Pug Mouse Cymric
     * {EgyptianMau=7, Pug=3, Rat=2, Cymric=7, Mouse=2, Cat=9, Manx=7, Rodent=5, Mutt=3, Dog=6, Pet=20, Hamster=1}
     *
     * 结果分析：
     * 1）types为allTypes子列表，通过subList(int fromIndex, int toIndex)获取子列表。
     *
     */
  }
}
