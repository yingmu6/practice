package relative.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.ClassFile;
import javassist.bytecode.FieldInfo;
import org.junit.Test;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @Author chenSy
 * @Date 2023/04/17 16:34
 * @Description
 */
public class JavassistTestV3 {

    /**
     * https://jse.readthedocs.io/en/latest/jdk8/javassistTutorial.html javassist Tutorial（讲解得很好）
     *
     * Javassist相关介绍
     * 1）Javassist is a class library for dealing with Java bytecode.（Javassist是用来处理java字节码的类库）
     * Java bytecode is stored in a binary file（二进制文件） called a class file（class文件）.
     * Each class file contains one Java class or interface.（每个class文件包含一个Java类或接口）
     *
     * 2）The class Javassist.CtClass is an abstract representation of a class file.（CtClass是class文件的抽象表示）
     * A CtClass (compile-time class) object is a handle for dealing with a class file.（CtClass：编译时的对象，用于处理class文件）
     *
     * 3）This program first obtains a ClassPool object, which controls bytecode modification with Javassist（项目首先需要一个ClassPool，用于控制字节码的操作）
     * The ClassPool object is a container of CtClass object representing a class file.（ClassPool是一个容器对象）
     *
     * 4）If a CtClass object is converted into a class file by writeFile(), toClass(), or toBytecode(), Javassist freezes that CtClass object.（CtClass对象写入到class文件时，CtClass对象会被冻结）
     * Further modifications of that CtClass object are not permitted.
     *
     * 5）使用javap可以查看字节码文件，如：javap -c Point.class
     *
     * 6）Javassist library can be used for generating new Java class files. (javassist可以用于产生新的class文件)
     *
     * 7）Since the compiler supports language extensions, several identifiers starting with $ have special meaning:
     * (javassist支持语言扩展，也就是使用$获取值)
     * 主要标识符功能）identifiers	    meaning
     *             $0, $1, $2, ...	this and actual parameters（实际参数值）
     *             $args	An array of parameters. The type of $args is Object[]（数组参数值）
     *             $r	The result type. It is used in a cast expression.（结果类型值）
     *             $w	The wrapper type. It is used in a cast expression.（封装类型值）
     *
     *
     */

    /**
     * 场景1：写一个可运行的用例（在可运行的前提下，解决JavassistTest、JavassistTestV2的运行问题）
     * 官网例子可用，https://www.javassist.org/tutorial/tutorial.html
     */
    @Test
    public void basicUse() throws Exception { // 对已有的类进行操作
        ClassPool cp = ClassPool.getDefault();
//        cp.appendClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader())); //此处可以不用

        // 获取已知的Class
        CtClass cc = cp.get("relative.javassist.Hello"); //此处需要全路径，使用"Hello"还找不到类，可做分析
        CtMethod m = cc.getDeclaredMethod("say");
        m.insertBefore("{ System.out.println(\"before say():\"); }"); //在方法调用前，插入代码
        m.insertAfter("{ System.out.println(\"after say():\"); }"); //在方法调用后，插入代码

        Class c = cc.toClass();
        Hello h = (Hello)c.newInstance();
        h.say();
    }

    @Test
    public void basicUseV2() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("relative.javassist.HelloV2");
        CtClass superCc = pool.get("java.io.Serializable");
        cc.setSuperclass(superCc);
        cc.writeFile(); //todo @csy 此处方法的用途是什么？
    }

    /**
     * 场景2：了解$获取值方式方式
     * 1）使用$1、$2等获取入参值
     * 2）了解$w的获取值方法
     *
     * 注意：
     * a）参数值，是从$1、$2开始取值的，$0取的是类实例信息，如relative.javassist.HelloV2@38364841
     * b）$type：输出返回值类型
     * c）$class：当前操作的类名，如class relative.javassist.HelloV2
     *
     * CtClass.debugDump这个特性很重要，可以把javassist产生的字节码文件，保存到指定目录
     * 这样一来，就可以看到字节码中有什么内容，可做验证
     * （Set CtClass.debugDump to a directory name. Then all class files modified and generated by
     * Javassist are saved in that directory. To stop this, set CtClass.debugDump to null. The default value is null）
     */
    @Test
    public void test_value() throws Exception {
        CtClass.debugDump = "./javassist-class";

        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("relative.javassist.HelloV2");

        CtMethod cm = cc.getDeclaredMethod("sayHello");
        cm.insertBefore("System.out.println($0 + \":aaa\");"); //在方法执行前调用
        cm.insertBefore("System.out.println($1 + \":bbb\");");
//        cm.insertBefore("System.out.println($w + \":ccc\");"); //todo 待确定
//        cm.insertBefore("System.out.println($greet + \":bbb\");");

        cm.insertBefore("System.out.println($type + \":ccc\");");
        cm.insertBefore("System.out.println($class + \":ddd\");");

        Class c = cc.toClass();
        HelloV2 helloV2 = (HelloV2) c.newInstance();
        helloV2.sayHello("你好");

    }

    /**
     * 场景3：部分零散点实践
     * 1）javassist.CtClass#detach()功能用途了解
     */

    /**
     * 场景4：了解CtClasss的冻结、解冻方法
     * 1）了解CtMethod、CtField联合字符串的使用
     * 2）CtNewMethod的copy以及make方法使用
     * 3）探索if (code.charAt(0) == ':')使用分号的场景
     */

    /**
     * 场景5：使用拼接的字符串，构造Class对象并实例化
     */

    /**
     * 场景6：使用Java SDK提供的API操作字节码，与javassist对比下
     */

    /**
     * 场景7：使用Javassist产生新的类，并设置对应的字段、方法
     */
    @Test
    public void test_generate_new_class_V1() throws Exception {
//        CtClass.debugDump = "./debug-class"; //输出调试目录，此处是没有效果的

        ClassFile cf = new ClassFile(false, "relative.javassist.Foo", null);
        cf.setInterfaces(new String[] { "java.lang.Cloneable" });
        FieldInfo f = new FieldInfo(cf.getConstPool(), "width", "I");
        f.setAccessFlags(AccessFlag.PUBLIC);
        cf.addField(f);

//        File file = new File("src/main/java/relative/javassist/Foo.class"); //输出字节码方式一：（可以输出，但产生的字节码是文本形式，没有高亮显示）
//        cf.write(new DataOutputStream(new FileOutputStream(file)));

        cf.write(new DataOutputStream(new FileOutputStream("Foo.class"))); //输出字节码文件二：
    }

    /**
     * 测试产生新的Class
     *
     * 问题点：
     * 1）insertClassPath的用途是怎样的？
     */
    @Test
    public void test_generate_new_class_V2() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath("./pool-class");

        CtClass cc = pool.makeClass("Apple");
        cc.addField(CtField.make("private int weight;", cc));
        cc.addMethod(CtMethod.make("public int getWeight() { return this.weight;}", cc));

        CtClass.debugDump = "./javassist-class"; //此处生效的
        cc.toClass();
        cc.writeFile();
    }

}
