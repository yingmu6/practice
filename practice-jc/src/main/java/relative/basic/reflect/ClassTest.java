package relative.basic.reflect;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author : chensy
 * Date : 2020/10/27 下午5:23
 */
public class ClassTest {

    /**
     * 场景1：isAssignableFrom方法的使用
     * 1）https://www.cnblogs.com/greatfish/p/6097507.html
     * 2）https://www.baeldung.com/java-isinstance-isassignablefrom  与instance of的差异（用例参考的来源）
     * 3）对应描述：
     * In other words, instanceof operator checks if the left object is same or subclass of right class,
     * while isAssignableFrom checks if we can assign object of the parameter class (from) to the reference of
     * the class on which the method is called.Note that both of these consider the actual instance not the reference type
     * （instanceof检查的是左边的class是否与右边的class相同，或者是其子class）
     * （isAssignableFrom检查的是“from：来自于” 参数的class是否可以赋值给调用方法的class ）
     *
     * 4）isAssignableFrom方法含义理解：
     * （形象理解：来自于（from）参数的引用class是否可以赋值给（Assignable）当前的class，使用表达式 this.Class = parameter.Class
     *           赋值语句：相同的类或子类/子接口，可以赋值。）
     * a）方法功能：（语义解释）判断当前的类或接口是否与指定的接口或类相等，或者是指定类或接口的超类或超接口。
     * b）方法名分析：isAssignableFrom，assignable：可分配的，可指定的，from：“来自”的意思
     * 注明：from对应的参数，需要是引用类型，如应该是shape.getClass()，而不是Shape.class
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

        assertFalse(shape.getClass().isAssignableFrom(Shape.class)); //false：参数类型需要是引用的Class
        assertTrue(shape.getClass().isAssignableFrom(shape.getClass())); //true：满足赋值表达式，如：shape.getClass() = shape.getClass();
        assertTrue(shape.getClass().isAssignableFrom(triangle.getClass()));
        assertTrue(shape.getClass().isAssignableFrom(isoscelesTriangle.getClass())); //true：满足赋值表达式，如：shape.getClass() = isoscelesTriangle.getClass()，IsoscelesTriangle是Shape子类
        assertTrue(shape.getClass().isAssignableFrom(isoscelesTriangle2.getClass()));

        assertFalse(triangle.getClass().isAssignableFrom(Shape.class)); //需要引用类型
        assertTrue(triangle.getClass().isAssignableFrom(shape.getClass())); //要看实际的引用类型，此处的shape的实际引用类型为：Triangle，所以triangle.getClass() = shape.getClass()是成立的
        assertTrue(triangle.getClass().isAssignableFrom(triangle.getClass())); //todo @pause
        assertTrue(triangle.getClass().isAssignableFrom(isoscelesTriangle.getClass()));
        assertTrue(triangle.getClass().isAssignableFrom(isoscelesTriangle2.getClass()));

        assertFalse(isoscelesTriangle.getClass().isAssignableFrom(Shape.class));
        assertFalse(isoscelesTriangle.getClass().isAssignableFrom(shape.getClass()));
        assertFalse(isoscelesTriangle.getClass().isAssignableFrom(triangle.getClass()));
        assertTrue(isoscelesTriangle.getClass().isAssignableFrom(isoscelesTriangle.getClass()));
        assertTrue(isoscelesTriangle.getClass().isAssignableFrom(isoscelesTriangle2.getClass()));

        assertFalse(isoscelesTriangle2.getClass().isAssignableFrom(Shape.class));
        assertFalse(isoscelesTriangle2.getClass().isAssignableFrom(shape.getClass()));
        assertFalse(isoscelesTriangle2.getClass().isAssignableFrom(triangle.getClass()));
        assertTrue(isoscelesTriangle2.getClass().isAssignableFrom(isoscelesTriangle.getClass()));
        assertTrue(isoscelesTriangle2.getClass().isAssignableFrom(isoscelesTriangle2.getClass()));
    }

    /**
     * 场景2：instanceof 与 isInstance使用
     */

    /**
     * 场景3：Method、Filed等使用
     * 1）Filed中的setAccessible(true)探索
     * 2）Filed的set(Object obj, Object value)方法使用，当obj=null时是什么含义？
     */

    /**
     * 场景4：newInstance方法使用
     */


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
