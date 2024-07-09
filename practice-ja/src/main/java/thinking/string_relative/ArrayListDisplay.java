package thinking.string_relative;

import thinking.generic_type.coffee.Coffee;
import thinking.generic_type.coffee.CoffeeGenerator;

import java.util.ArrayList;

/**
 * @author orange
 * @date 2024/6/4
 */
public class ArrayListDisplay { //TkY-Doing

    /**
     * 知识点（13.3）：无意识的递归
     *
     * 问题点答疑：
     * 1）为什么叫作"无意识的递归" ？
     */
    public static void main(String[] args) { //Doing
        ArrayList<Coffee> coffees = new ArrayList<>();
        for (Coffee c : new CoffeeGenerator()) {
            coffees.add(c);
        }
        System.out.println(coffees);

        /**
         * 输出结果：
         * []
         *
         * 结果分析：
         *
         */
    }

}
