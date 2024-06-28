package thinking.enum_type;
import java.util.*;

public class BigEnumSet { //@TkY-Doing

  /**
   * 知识点：
   */

  enum Big { A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10,
    A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21,
    A22, A23, A24, A25, A26, A27, A28, A29, A30, A31, A32,
    A33, A34, A35, A36, A37, A38, A39, A40, A41, A42, A43,
    A44, A45, A46, A47, A48, A49, A50, A51, A52, A53, A54,
    A55, A56, A57, A58, A59, A60, A61, A62, A63, A64, A65,
    A66, A67, A68, A69, A70, A71, A72, A73, A74, A75 }

  public static void main(String[] args) { //Doing
    EnumSet<Big> bigEnumSet = EnumSet.allOf(Big.class);
    System.out.println(bigEnumSet);

    /**
     * 输出结果：
     * [A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16,
     * A17, A18, A19, A20, A21, A22, A23, A24, A25, A26, A27, A28, A29, A30, A31,
     * A32, A33, A34, A35, A36, A37, A38, A39, A40, A41, A42, A43, A44, A45, A46,
     * A47, A48, A49, A50, A51, A52, A53, A54, A55, A56, A57, A58, A59, A60, A61,
     * A62, A63, A64, A65, A66, A67, A68, A69, A70, A71, A72, A73, A74, A75]
     *
     * 结果分析：
     * 1）EnumSet的offOf方法：创建包含指定枚举类型所有元素的EnumSet
     *
     * 问题点答疑：
     * 1）在EnumSet#getUniverse方法中的SharedSecrets.getJavaLangAccess()中获取到的成员变量javaLangAccess，是什么时候被初始化的？
     *
     * 2）EnumSet里面的操作有用到native方法吗？还是只是Java内部的逻辑处理？
     *
     */

  }
}
