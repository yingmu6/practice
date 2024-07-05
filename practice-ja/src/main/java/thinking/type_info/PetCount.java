package thinking.type_info;
// Using instanceof.


import thinking.type_info.pets.*;

import java.util.HashMap;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

public class PetCount { //@TkY-Doing

  /**
   * 知识点（14.3）
   *
   * 知识点概括：
   * 1）
   */
  static class PetCounter extends HashMap<String, Integer> { //静态内部类
    public void count(String type) {
      Integer quantity = get(type);
      if (quantity == null)
        put(type, 1); //从HashMap中继承来的方法
      else
        put(type, quantity + 1);
    }
  }

  public static void countPets(PetCreator creator) {
    PetCounter counter = new PetCounter();
    for (Pet pet : creator.createArray(20)) {
      // List each individual pet:
      printnb(pet.getClass().getSimpleName() + " ");
      if (pet instanceof Pet)
        counter.count("Pet");
      if (pet instanceof Dog)
        counter.count("Dog");
      if (pet instanceof Mutt)
        counter.count("Mutt");
      if (pet instanceof Pug)
        counter.count("Pug");
      if (pet instanceof Cat)
        counter.count("Cat");
      if (pet instanceof Manx)
        counter.count("EgyptianMau");
      if (pet instanceof Manx)
        counter.count("Manx");
      if (pet instanceof Manx)
        counter.count("Cymric");
      if (pet instanceof Rodent)
        counter.count("Rodent");
      if (pet instanceof Rat)
        counter.count("Rat");
      if (pet instanceof Mouse)
        counter.count("Mouse");
      if (pet instanceof Hamster)
        counter.count("Hamster");
    }
    // Show the counts:
    print();
    print(counter);
  }

  public static void main(String[] args) { //Done
    countPets(new ForNameCreator());

    /**
     * 输出结果：
     * Rat Manx Cymric Mutt Pug Cymric Pug Manx Cymric Rat EgyptianMau Hamster EgyptianMau Mutt Mutt Cymric Mouse Pug Mouse Cymric
     * {EgyptianMau=7, Pug=3, Rat=2, Cymric=7, Mouse=2, Cat=9, Manx=7, Rodent=5, Mutt=3, Dog=6, Pet=20, Hamster=1}
     *
     * 结果分析:
     * 1）ForNameCreator类初始化时，会通过static块把pets包下的类通过forName方式获取Class对象，并存储起来。
     *   然后通过creator.createArray(20)方式随机取出Class对象，然后通过instanceof与具体的实例做判断，并累加
     *   出现的次数。
     *
     */
  }
}