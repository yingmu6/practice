package thinking.access_control;

import thinking.access_control.ext.CakeExt;
import thinking.access_control.ext.PieExt;

/**
 * @author chensy
 * @date 2024/4/30
 */
public class Cake extends CakeExt { //@TkY-Doing

    /**
     * 知识点（6.2.2）：默认包
     *
     * 问题点答疑：
     * 1）protected在不同包的子类中可以访问，在不同包的非子类不能访问，是怎么体现出来的？
     *
     * 参考链接：
     * 1）Java中四种访问权限 https://blog.csdn.net/itachiyang/article/details/43647909
     * 2）深入理解Java的四种访问权限 https://www.cnblogs.com/java-chen-hao/p/10399947.html
     */
    public static void main(String[] args) {
        Pie x = new Pie();
        x.f(); //此处Cake与Pie同在一个包下，所以可以访问Pie的默认方法

        PieExt pieExt = new PieExt();
        /**
         * 此处会抛出语法错误：'f()' is not public in 'thinking.access_control.ext.PieExt'. Cannot be accessed from outside package
         * 即：f()是protected访问权限，只有同一个包下才可以访问，Cake与PieExt是不同包下的类，且不存在继承关系
         */
        //pieExt.f();
        pieExt.g();

        // x.h(); //此处编译错误，因为h()访问权限是private，
        x.accessH(); //通过类中可访问的方法，间接访问h()

        CakeExt cakeExt = new Cake();
        // cakeExt.f();

        /**
         * 输出结果：
         * Pie.f()
         * ext.Pie g()
         * Pie.h()
         *
         * 结果分析：
         * 1）访问权限对应
         *
         * 问题点答疑：
         * 1）cakeExt.f()为啥访问不了？
         */
    }
}
