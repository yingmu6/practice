package thinking.enum_type;

import static thinking.enum_type.Spiciness.*;

public class Burrito { //@TkY-Doing

  /**
   * 知识点：
   */
  Spiciness degree;
  public Burrito(Spiciness degree) { this.degree = degree;}
  public String toString() { return "Burrito is "+ degree;}

  public static void main(String[] args) { //Doing
    System.out.println(new Burrito(NOT));
    System.out.println(new Burrito(MEDIUM));
    System.out.println(new Burrito(HOT));
    System.out.println(new Burrito(HaHa));

    /**
     * 输出结果：
     * Burrito is NOT
     * Burrito is MEDIUM
     * Burrito is HOT
     * Burrito is HaHa
     *
     * 结果分析：
     * 1）打印出枚举的名称，即为声明的名称，大小写都可以（只是一般大小，表明是常量）
     *
     * 问题点答疑：
     * 1）如何使用java命令，看到反编译的内容（或者通过idea插件）
     */
  }
}
