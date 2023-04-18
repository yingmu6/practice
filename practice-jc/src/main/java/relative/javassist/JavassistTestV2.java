package relative.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Loader;
import javassist.LoaderClassPath;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author : chensy
 * Date : 2020/11/12 上午9:49
 */
public class JavassistTestV2 {

    /**
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
     */

    private static ClassPool CLASS_POOL;

    private static CtClass ANIMAL_CT_CLASS;

    private static CtClass FRUIT_CT_CLASS;

     /**
     * 场景1：了解$获取值方式方式
     * 1）使用$1、$2等获取入参值
     * 2）了解$w的获取值方法
     */
    @Before
    public void init() throws Exception{
        CLASS_POOL = ClassPool.getDefault();
        CLASS_POOL.appendClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader())); //方式二
        ANIMAL_CT_CLASS = CLASS_POOL.get(Animal.class.getName());
        FRUIT_CT_CLASS = CLASS_POOL.get(Fruit.class.getName());
    }

    /**
     * 修改已有的方法
     *   可以通过对象调用增强的方法
     */
    @Test
    public void editMethod() throws Exception{
        CtMethod ctMethod = FRUIT_CT_CLASS.getDeclaredMethod("showInfo");
        ctMethod.insertBefore("System.out.println(\"增强代码 !!!\");");
//        Class<Fruit> fruitClass = FRUIT_CT_CLASS.toClass();
//        Fruit fruit = (Fruit)FRUIT_CT_CLASS.toClass().newInstance(); //2) 两处都可以创建Class

        CtClass ctClass = CLASS_POOL.get("relative.javassist.Fruit");

        Loader classLoader = new Loader(CLASS_POOL);
//        Fruit fruit = (Fruit) ctClass.toClass().newInstance();

        Fruit fruit = (Fruit) classLoader.loadClass("relative.javassist.Fruit").newInstance();
        fruit.showInfo();
    }

    /**
     * 使用反射机制获取新增的方法
     */
    private static void addMethod(CtClass ctClass) throws Exception {
        //CtClass returnType, String mname,CtClass[] parameters, CtClass declaring
        CtClass[] parameters = new CtClass[1];
        parameters[0] = CtClass.intType;

        CtMethod ctMethod = new CtMethod(CtClass.intType, "sayHello", parameters,  ctClass);
        ctMethod.setBody("{System.out.println($1);return 1;}"); //怎么指定参数名称，解：使用占位符$来执行
        ctClass.addMethod(ctMethod);

        // 加载修改后的类
        ctClass.toClass();

        /**
         * 创建实例
         * 1）加载后的类使用newInstance()
         * 2）直接new对象
         */
//        Fruit fruit = fruitClass.newInstance(); 1）
//        Method method = fruit.getClass().getMethod("sayHello", new Class[]{int.class});
//        method.invoke(fruit, 112); //invoke需要传入实例对象

        Fruit fruit = new Fruit(); // 2)
        Method method = Fruit.class.getMethod("sayHello", new Class[]{int.class});
        method.invoke(fruit, 112);
    }

    /**
     * 没看到有String类型，和引用类型，怎么支持的？
     * 解：参数中可以添加引用类型
     * 如：获取String类型 CtClass stringClass = pool.get("java.lang.String");
     *    获取list类型 CtClass listClass = pool.get("java.util.List");
     */
    private static void addMethodV2(ClassPool classPool, CtClass ctClass) throws Exception {
        CtClass stringClass = classPool.getCtClass("java.lang.String");
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "test", new CtClass[] {stringClass}, ctClass);
        ctMethod.setBody("{System.out.println($1);}");
        ctClass.addMethod(ctMethod);

        ctClass.toClass();
        Fruit fruit = new Fruit();
        Method method = Fruit.class.getMethod("test", new Class[] {String.class});
        method.invoke(fruit, "你好");

    }

    /**
     * 场景2：了解CtClasss的冻结、解冻方法
     */

    /**
     * 场景3：使用拼接的字符串，构造Class对象并实例化
     */

    /**
     * 场景4：使用Java SDK提供的API操作字节码，与javassist对比下
     */


    // 增加字段和方法，直接调用会报编译错误，需要通过反射机制获取
    // https://zhuanlan.zhihu.com/p/141449080  Java动态字节技术之Javassist
    // http://www.javassist.org/html/ API 文档

}
