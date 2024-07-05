package thinking.type_info;

import thinking.type_info.pets.Pets;

public class PetCount2 { //@TkY-Doing

  /**
   * 知识点：
   *
   * 知识点概括：
   * 1）
   *
   */

  public static void main(String[] args) { //Done
    PetCount.countPets(Pets.creator);

    /**
     * 输出结果：
     * Rat Manx Cymric Mutt Pug Cymric Pug Manx Cymric Rat EgyptianMau Hamster EgyptianMau Mutt Mutt Cymric Mouse Pug Mouse Cymric
     * {EgyptianMau=7, Pug=3, Rat=2, Cymric=7, Mouse=2, Cat=9, Manx=7, Rodent=5, Mutt=3, Dog=6, Pet=20, Hamster=1}
     *
     * 结果分析：
     * 1）Pets.creator是用LiteralPetCreator列表的形式Class对象
     */
  }
}
