package relative.basic.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author : chensy
 * Date : 2020/10/27 下午5:23
 */
public class ClassTest { //@JcY-Doing

    /**
     * 知识点：isAssignableFrom方法的使用
     *
     * 知识点概括：
     * 1）In other words, instanceof operator checks if the left object is same or subclass of right class,
     * while isAssignableFrom checks if we can assign object of the parameter class (from) to the reference of
     * the class on which the method is called.Note that both of these consider the actual instance not the reference type
     * （instanceof检查的是左边的class是否与右边的class相同，或者是其子class）
     * （isAssignableFrom检查的是“from：来自于” 参数的class是否可以赋值给调用方法的class ）
     *
     * 2）isAssignableFrom方法含义理解：
     * （形象理解：来自于（from）参数的引用class是否可以赋值给（Assignable）当前的class，使用赋值语句表示：this.Class = parameter.Class
     *           赋值语句：相同的类或子类/子接口，可以赋值。）
     * a）方法功能：（语义解释）判断当前的类或接口是否与指定的接口或类相等，或者是指定类或接口的超类或超接口。
     * b）方法名分析：isAssignableFrom，assignable：可分配的，可指定的，from：“来自”的意思
     * 注明：from对应的参数，需要是引用类型，如应该是shape.getClass()，而不是Shape.class
     *
     * 3）总结：明确当前调用方案的Class和参数Class，然后使用复制语句判断 this.Class = parameter.Class
     *
     * 问题点答疑：
     * 1）Class类的本质是什么？
     *
     * 参考链接：
     * 1）https://www.cnblogs.com/greatfish/p/6097507.html
     * 2）https://www.baeldung.com/java-isinstance-isassignablefrom  与instance of的差异（用例参考的来源）
     */
    @Test
    public void test_isAssignableFrom() {
        /**
         * 继承关系：
         * 1）IsoscelesTriangle extends Triangle
         * 2）Triangle extends Shape
         */
        Shape shape = new Triangle(); //图形
        Triangle triangle = new Triangle(); //三角形
        IsoscelesTriangle isoscelesTriangle = new IsoscelesTriangle(); //等腰三角形
        Triangle isoscelesTriangle2 = new IsoscelesTriangle();

        System.out.println(shape.getClass().isAssignableFrom(Shape.class)); //false：参数类型需要是引用的Class
        System.out.println(shape.getClass().isAssignableFrom(shape.getClass())); //true：满足赋值表达式，如：shape.getClass() = shape.getClass();
        System.out.println(shape.getClass().isAssignableFrom(triangle.getClass()));
        System.out.println(shape.getClass().isAssignableFrom(isoscelesTriangle.getClass())); //true：满足赋值表达式，isoscelesTriangle是shape的间接子类
        System.out.println(shape.getClass().isAssignableFrom(isoscelesTriangle2.getClass()));
        System.out.println(triangle.getClass().isAssignableFrom(Shape.class)); //需要引用类型
        System.out.println(triangle.getClass().isAssignableFrom(shape.getClass())); //要看实际的引用类型，此处的shape的实际引用类型为：Triangle，所以triangle.getClass() = shape.getClass()是成立的
        System.out.println(triangle.getClass().isAssignableFrom(triangle.getClass()));
        System.out.println(triangle.getClass().isAssignableFrom(isoscelesTriangle.getClass()));

        /**
         * 输出结果：
         *
         * false
         * true
         * true
         * true
         * true
         * false
         * true
         * true
         * true
         */
    }

    /**
     * 场景2：instanceof使用
     *（instanceof检查的是左边的class是否与右边的class相同，或者是其子class）
     * 形象理解：instanceof的作用可以用不等式表示：对象的class <= 类的class
     *
     * 注意：
     * 1）空对象不是任何类型的实例，即 空对象 instanceof XXX，始终为false
     * 2）instanceof左边的对象类型，要看具体赋值的对象类型，如Shape shape = new Triangle();中
     *   shape的实际类型为Triangle，要按实际类型类比较
     *
     * 总结：找到引用的实际类型，然后应用不等式来判断， instanceof使用不等式 -》引用的实际类型Class <= 类的Class
     */
    @Test
    public void test_instanceof() {
        Shape shape = new Triangle();
        Triangle triangle = new Triangle();
        IsoscelesTriangle isoscelesTriangle = new IsoscelesTriangle();
        Shape nonspecificShape = null;

        System.out.println(shape instanceof Shape); //shape引用的实际类型为Triangle，Triangle.class <= Shape.class成立，即返回true
        System.out.println(triangle instanceof Shape); //triangle引用的实际类型为Triangle，Triangle.class <= Shape.class成立，即返回true
        System.out.println(isoscelesTriangle instanceof Shape);
        System.out.println(nonspecificShape instanceof Shape); //空对象

        System.out.println(shape instanceof Triangle); //shape对应的实际类型为Triangle，所以是Triangle类型的实例
        System.out.println(triangle instanceof Triangle);
        System.out.println(isoscelesTriangle instanceof Triangle);
        System.out.println(nonspecificShape instanceof Triangle);

        System.out.println(shape instanceof IsoscelesTriangle); //shape对应的实际类型为Triangle，Triangle.class <= IsoscelesTriangle.class 不成立，即返回false
        System.out.println(triangle instanceof IsoscelesTriangle);
        System.out.println(isoscelesTriangle instanceof IsoscelesTriangle);
        System.out.println(nonspecificShape instanceof IsoscelesTriangle);
    }

    /**
     * 场景3：isInstance使用
     * 功能描述：来自于java源码
     * Determines if the specified {@code Object} is assignment-compatible
     * with the object represented by this {@code Class}.  This method is
     * the dynamic equivalent of（等价于） the Java language {@code instanceof} operator.
     * a）决定 指定的对象是否是给出的类型的实例
     * b）等价于 instanceof的方向操作，即可以使用不等式来判断，类的class >= 对象的class
     * c）instanceof是java关键字，而isInstanceof是Class中的方法。
     *
     * 总结：找到引用的实际类型，然后应用不等式来判断，isInstance使用不等式 -》类的Class >= 引用的实际类型Class
     */
    @Test
    public void test_isInstance() {
        Shape shape = new Triangle();
        Triangle triangle = new Triangle();
        IsoscelesTriangle isoscelesTriangle = new IsoscelesTriangle();
        Triangle isoscelesTriangle2 = new IsoscelesTriangle();
        Shape nonspecificShape = null;

        System.out.println(Shape.class.isInstance(shape)); //shape引用的实际类型为Triangle，Shape.class >= Triangle.class 成立，即返回true
        System.out.println(Shape.class.isInstance(triangle));
        System.out.println(Shape.class.isInstance(isoscelesTriangle));
        System.out.println(Shape.class.isInstance(isoscelesTriangle2));
        System.out.println(Shape.class.isInstance(nonspecificShape)); //空对象不是任何类型的实例

        System.out.println(Triangle.class.isInstance(shape));
        System.out.println(Triangle.class.isInstance(triangle));
        System.out.println(Triangle.class.isInstance(isoscelesTriangle));
        System.out.println(Triangle.class.isInstance(isoscelesTriangle2));

        System.out.println(IsoscelesTriangle.class.isInstance(shape)); //shape引用的实际类型为Triangle，IsoscelesTriangle.class >= Triangle.class 不成立，即返回false
        System.out.println(IsoscelesTriangle.class.isInstance(triangle));
        System.out.println(IsoscelesTriangle.class.isInstance(isoscelesTriangle));
        System.out.println(IsoscelesTriangle.class.isInstance(isoscelesTriangle2));
    }

    /**
     * 场景4：Method、Filed、Constructor等使用
     * 1）Filed中的setAccessible(true)探索
     * 2）Filed的set(Object obj, Object value)方法使用，当obj=null时是什么含义？
     *
     * 反射机制的描述：
     * 1）Reflection is a feature in the Java programming language. It allows an executing Java program to examine or
     * "introspect"（内省：自我观察） upon itself, and manipulate（操作） internal properties of the program.
     * For example, it's possible for a Java class to obtain the names of all its members and display them
     * （反射式java编程语言的特性，在java程序运行时，自我内省、操作内部的属性）
     *
     * 2）The ability to examine（检查） and manipulate a Java class from within itself may not sound like very much,
     * but in other programming languages this feature simply doesn't exist. For example,
     * there is no way in a Pascal, C, or C++ program to obtain information about the functions defined within that program.
     * （从自身内部检查和操作Java类的能力可能听起来不是很强大，但在其它编程语言中是不存在这个特性的）
     *
     * 3）One tangible（明确的） use of reflection is in JavaBeans, where software components can be manipulated visually（可视化的） via a builder tool.
     * The tool uses reflection to obtain the properties of Java components (classes) as they are dynamically loaded
     * （反射的一个实际应用是在JavaBeans中，其中软件组件可以通过构建器工具进行可视化操作。当Java组件(类)被动态加载时，该工具使用反射来获取它们的属性）
     *
     * 参考链接：
     * a）https://www.oracle.com/technical-resources/articles/java/javareflection.html oracle官网介绍（当前测试用例的来源处）
     * b）https://www.baeldung.com/java-reflection 反射机制介绍
     */
    @Test
    public void test_reflection_methods() {

        // 获取声明的方法（不包含继承的方法）
        try {
            Class c = Class.forName("relative.basic.reflect.GreetServiceImpl");
            Method m[] = c.getDeclaredMethods();
            for (int i = 0; i < m.length; i++)
                System.out.println(m[i].toString());
        }
        catch (Throwable e) {
            System.err.println(e);
        }
    }

    @Test
    public void test_reflection_parameterType() { //测试Method的参数类型

        try {
            Class cls = Class.forName("relative.basic.reflect.GreetServiceImpl");
            Method methodList[] = cls.getDeclaredMethods();
            for (int i = 0; i < methodList.length; i++) {
                Method m = methodList[i];
                System.out.println("方法名 name：" + m.getName());
                System.out.println("所在的类 decl class = " + m.getDeclaringClass());
                Class[] paramTypes = m.getParameterTypes(); //获取方法中的参数类型
                for (int j = 0; j < paramTypes.length; j++) {
                    System.out.println("参数信息 param #" + j + " " + paramTypes[j]);
                }

                Class[] exceptionTypes = m.getExceptionTypes(); //获取方法中声明的异常类型
                for (int j = 0; j < exceptionTypes.length; j++) {
                    System.out.println("异常信息 exc #" + j + " " + exceptionTypes[j]);
                }

                System.out.println("返回类型 return type = " + m.getReturnType());
                System.out.println("-----");
            }
        }
        catch (Throwable e) {
            System.err.println(e);
        }
    }

    @Test
    public void test_reflection_constructor() { //测试构造函数

        try {
            Class cls = Class.forName("relative.basic.reflect.GreetServiceImpl");
            Constructor constructors[] = cls.getDeclaredConstructors(); //获取声明的构造方法列表
            for (int i = 0; i < constructors.length; i++) {
                Constructor ct = constructors[i];
                System.out.println("构造方法 name" + ct.getName());
                System.out.println("所在的类 decl class = " + ct.getDeclaringClass());

                Class parameterTypes[] = ct.getParameterTypes(); //获取构造方法的参数
                for (int j = 0; j < parameterTypes.length; j++) {
                    System.out.println("参数信息 param #" + j + " " + parameterTypes[j]);
                }

                Class exceptionTypes[] = ct.getExceptionTypes();
                for (int j = 0; j < exceptionTypes.length; j++) { //获取构造方法上声明的异常
                    System.out.println("异常信息 exc #" + j + " " + exceptionTypes[j]);
                }
                System.out.println("-----");
            }
        }
        catch (Throwable e) {
            System.err.println(e);
        }
    }

    @Test
    public void test_reflection_field() { //测试字段信息

        try {
            Class cls = Class.forName("relative.basic.reflect.GreetServiceImpl");

            Field fieldList[] = cls.getDeclaredFields(); //获取声明的字段列表
            for (int i = 0; i < fieldList.length; i++) {
                Field fld = fieldList[i];
                System.out.println("字段名 name " + fld.getName()); //获取字段名
                System.out.println("所属的类 decl class = " + fld.getDeclaringClass());
                System.out.println("字段类型 type " + fld.getType()); //获取字段类型
                int mod = fld.getModifiers(); //修饰符为数值型
                System.out.println("字段的修饰符 modifiers = " + Modifier.toString(mod)); //将数值型的修饰符转换为字符串
                System.out.println("-----");
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }

    @Test
    public void test_reflection_invoke() { //测试方法调用
        try {
            Class cls = Class.forName("relative.basic.reflect.GreetServiceImpl");

            Class parameters[] = new Class[1];
            parameters[0] = String.class;
            Method method = cls.getMethod("say", parameters); //获取指定方法名、参数的Method对象

            Object result = method.invoke(cls.newInstance(), "haha"); //通过Method对目标对象的方法进行调用
            System.out.println((String) result);
        } catch (Throwable e) {
            System.err.println(e);
        }
    }

    /**
     * 场景5：测试Class#getComponentType()方法
     */
    @Test
    public void testComponentType() {
        String[] arr = new String[3];
        System.out.println(Triangle.class.getComponentType());
        System.out.println(arr.getClass().getComponentType());

        /**
         *
         * 输出结果：
         * null
         * class java.lang.String
         *
         * 结果分析：
         * getComponentType()： Returns the Class representing the component type of an array.
         * （返回class类对应的数组类型，不是数组的，返回null）
         */
    }

    /**
     * 场景6：todo @csy-23/07/29 Filed的synthetic
     *
     * 参考链接：https://juejin.cn/post/7028113836028755982
     */
    @Test
    public void test_isSynthetic() {

    }

    /**
     * 场景7：测试Class的名称、简称等
     */
    @Test
    public void testClassName() {
        System.out.println(Shape.class.getSimpleName() + "，" + Shape.class.getName() + "，" + Shape.class.getCanonicalName());

        /**
         * 输出结果：
         * Shape，relative.basic.reflect.ClassTest$Shape，relative.basic.reflect.ClassTest.Shape
         *
         * 结果分析：
         *
         * 结果概括：
         */
    }

    /**
     * ------测试用例--------
     */
    public interface Shape { //图形
    }

    public class Triangle implements Shape { //三角形
    }

    public class IsoscelesTriangle extends Triangle { //等腰三角形
    }
}
