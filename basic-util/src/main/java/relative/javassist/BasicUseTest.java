package relative.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.reflect.Method;

/**
 * @author : chensy
 * Date : 2020/11/12 上午9:49
 */
public class BasicUseTest {
    public static void main(String[] args) throws Exception {
        // 此处用户 + ctClass.toClass()，会出现attempted  duplicate class definition for name: "relative/javassist/Fruit"
        // 因为Fruit已经产生一个Class对象，然后再通过ClassPool.toClass()又产生一个Class，所以就报错了
//       Fruit fruit = new Fruit(20.5, 2);
//       System.out.println(fruit.buyNum());
//       fruit.showInfo();

       // 创建类库池：
       ClassPool classPool = ClassPool.getDefault();
       // 获取指定类名的CtClass
       CtClass ctClass = classPool.get("relative.javassist.Fruit");

//       editMethod(ctClass);
//        addMethod(ctClass);
        addMethodV2(classPool, ctClass);
    }

    /**
     * 修改已有的方法
     *   可以通过对象调用增强的方法
     */
    private static void editMethod(CtClass ctClass) throws Exception{
        CtMethod ctMethod = ctClass.getDeclaredMethod("showInfo");
        ctMethod.insertBefore("System.out.println(\"增强代码 !!!\");");
//        Class<Fruit> fruitClass = ctClass.toClass(); 1）
        Fruit fruit = (Fruit)ctClass.toClass().newInstance(); //2) 两处都可以创建Class
//        Fruit fruit = fruitClass.newInstance();
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
          CtMethod ctMethod = new CtMethod(CtClass.voidType, "test", new CtClass[]{stringClass}, ctClass);
          ctMethod.setBody("{System.out.println($1);}");
          ctClass.addMethod(ctMethod);

          ctClass.toClass();
          Fruit fruit = new Fruit();
          Method method = Fruit.class.getMethod("test", new Class[]{String.class});
          method.invoke(fruit, "你好");

    }

    // 增加字段和方法，直接调用会报编译错误，需要通过反射机制获取
    // https://zhuanlan.zhihu.com/p/141449080  Java动态字节技术之Javassist
    // http://www.javassist.org/html/ API 文档
}
