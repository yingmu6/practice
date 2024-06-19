package thinking.holder_obj;//: holding/PetMap.java
import thinking.type_info.pets.Cat;
import thinking.type_info.pets.Dog;
import thinking.type_info.pets.Hamster;
import thinking.type_info.pets.Pet;

import java.util.*;
import static net.mindview.util.Print.*;

public class PetMap {

  /**
   * 知识点（11.10）：Map
   */

  public static void main(String[] args) {
    Map<String,Pet> petMap = new HashMap<String, Pet>();
    petMap.put("My Cat", new Cat("Molly"));
    petMap.put("My Dog", new Dog("Ginger"));
    petMap.put("My Hamster", new Hamster("Bosco"));
    print(petMap);
    Pet dog = petMap.get("My Dog");
    print(dog);
    print(petMap.containsKey("My Dog"));
    print(petMap.containsValue(dog));
  }
} /* Output:
{My Cat=Cat Molly, My Hamster=Hamster Bosco, My Dog=Dog Ginger}
Dog Ginger
true
true
*///:~
