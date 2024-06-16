package interview.written_exam.basic;

import org.junit.Test;

import java.io.*;

/**
 * @author chensy
 * @date 2023/7/27
 */
public class ExceptionTest { //@MsY-Done

    /**
     * 知识点：异常类型
     *
     * 知识点概括：
     * 1）异常的分类：
     *    a）检查性异常：最具代表的检查性异常是用户错误或问题引起的异常，这是程序员无法预见的。这些异常在编译时不能被简单地忽略。（编译期间发现的异常）
     *    b）运行时异常： 运行时异常是可能被程序员避免的异常。与检查性异常相反，运行时异常可以在编译时被忽略。（运行期间发现的异常）
     *    c）错误： 错误不是异常，而是脱离程序员控制的问题。错误在代码中通常被忽略。例如，当栈溢出时，一个错误就发生了，它们在编译也检查不到的。
     *
     * 2）使用try-with-resource释放资源，避免造成内存泄露
     *
     * 参考链接：
     * a）https://www.baeldung.com/java-exceptions（其用例可参考）
     */

    /**
     * 场景1：运行时异常和检查时异常
     */
    @Test
    public void test_checked_exception() { //Done
        try {
            doSomething();
        } catch (Exception e) {
           e.printStackTrace();
        }
        // doSomething2(); // 此处需要捕获异常，因为IOException属于检查时异常

        /**
         * 结果分析：
         * 1）运行时异常：如ArithmeticException，在执行的方法中如doSomething()
         *    是不需要加上try/catch，或在方法上声明异常的。
         * 2）检查时异常：如FileNotFoundException、IOException，就要在方法中如doSomething2()
         *    显示的加上try/catch，或在方法上声明异常的，在编译期就会检查。
         */
    }

    private void doSomething() { //ArithmeticException是运行时异常，继承了RuntimeException（可以在编译器被忽略）
        System.out.println(1 / 0);
    }

    private void doSomething2() throws IOException {
        File file = new File("/test.txt");
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        is.read();
    }

    /**
     * 场景2：使用try-with-resources释放资源
     */
    @Test
    public void test_release_v1() throws FileNotFoundException { //Done_jdk1.7的写法
        // 第一种写法：借助额外引用（不够简洁）
        InputStream fis = new FileInputStream("src/main/resources/input.txt"); //此处要注意：把相关文件放在resources下，相对路径才可用，不然就得像下面使用绝对路径了
//         InputStream fis = new FileInputStream("/Users/shengyong.chen/self_pro/practice/practice-qt/src/main/java/com/csy/interview/no2/exception_ext/input2.txt");
        try(InputStream fis1 = fis) {
            while (fis1.read() != -1) {
                System.out.println(fis1.read());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 第二种写法：在try中直接创建流 (try中比较臃肿，看上去不优雅，不够简洁)
        try(InputStream fis2 = new FileInputStream("src/main/resources/input.txt")) {
            while (fis2.read() != -1) {
                System.out.println(fis2.read());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 结果分析：
         * 1）使用try-with-resources释放资源，避免忘记释放资源导致的内存泄露
         */
    }

    @Test
    public void test_release_v2() throws FileNotFoundException { //Done_jdk1.9后的写法（写法更加简洁，切换jdk版本到1.9后可运行）
//        InputStream fis = new FileInputStream("src/main/resources/input.txt");
//        try(fis) { //jdk1.9之前是会报语法错误的
//            while (fis.read() != -1) {
//                System.out.println(fis.read());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
