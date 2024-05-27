package thinking.polymorphism;

/**
 * @author chensy
 * @date 2024/3/29
 */
public class PrivateOverride {

    /**
     * 知识点：（缺陷："覆盖"私有方法）
     *
     * 知识点概括：
     * 1）
     */
    static class Derived extends PrivateOverride {
        public void fn() { //代码C
            System.out.println("Derived public fn()");
        }

//        private void fn() { //代码D
//            System.out.println("Derived private fn()");
//        }
    }

    private void fn() { //代码B
        System.out.println("PrivateOverride private fn()");
    }

//    public void fn() { //代码A
//        System.out.println("PrivateOverride public fn()");
//    }

    public static void main(String[] args) {
        PrivateOverride po = new Derived();
        po.fn();

        /**
         * 输出结果：
         * PrivateOverride private fn()
         *
         * 结果分析：
         * 1）因为父类PrivateOverride中的fn()是private，而子类Derived中的fn()是public
         *    子类的fn()方法没有覆盖父类的方法，所以输出父类的fn()方法
         *
         * 2）父类与子类fn()方法各个场景分析：
         *   2.1）代码A + 代码C，输出 "Derived public fn()"，即子类覆盖了父类fn()方法
         *   2.2）代码B + 代码C，输出 "PrivateOverride private fn()" ，即子类覆盖不了父类的private方法
         *   2.3）代码B + 代码D，输出 "PrivateOverride private fn()" ，同2.2）相同结果
         *   2.4）代码A + 代码D，报"访问权限"的编译错误，即不能把父类的public方法重写为private方法
         */
    }
}
