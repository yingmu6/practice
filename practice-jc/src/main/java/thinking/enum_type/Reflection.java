package thinking.enum_type;

import net.mindview.util.OSExecute;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/8
 */
public class Reflection { //@thinking Done

    /**
     * 知识点：values()的神秘之处
     * 1）values()方法是枚举类在编译后，编译器加的。并且枚举类为final，不能被继承的
     */

    enum Explore {
        HERE, THERE
    }

    public static Set<String> analyze(Class<?> enumClass) {
        print("------ Analyzing " + enumClass + " ---------");
        print("Interfaces:");
        for (Type t : enumClass.getGenericInterfaces()) {
            print(t);
        }
        print("Base：" + enumClass.getSuperclass());
        print("Methods：");
        Set<String> methods = new TreeSet<>();
        for (Method m : enumClass.getMethods()) {
            methods.add(m.getName());
        }
        print(methods);
        return methods;
    }

    public static void main(String[] args) {
        Set<String> exploreMethods = analyze(Explore.class);
        Set<String> enumMethods = analyze(Enum.class);
        print("Explore.containsAll(Enum)? " + exploreMethods.containsAll(enumMethods));
        print("Explore.removeAll(enumMethods)");
        exploreMethods.removeAll(enumMethods);
        print(exploreMethods);
        OSExecute.command("javap /Users/shengyong.chen/self_remote/practice/practice-jc/target/classes/thinking/enum_type/case2/Explore.class");

        /**
         * 输出结果：
         * ------ Analyzing class thinking.enum_type.case2.Explore ---------
         * Interfaces:
         * Base：class java.lang.Enum
         * Methods：
         * [compareTo, equals, getClass, getDeclaringClass, hashCode, name, notify, notifyAll, ordinal, toString, valueOf, values, wait]
         * ------ Analyzing class java.lang.Enum ---------
         * Interfaces:
         * java.lang.Comparable<E>
         * interface java.io.Serializable
         * Base：class java.lang.Object
         * Methods：
         * [compareTo, equals, getClass, getDeclaringClass, hashCode, name, notify, notifyAll, ordinal, toString, valueOf, wait]
         * Explore.containsAll(Enum)? true
         * Explore.removeAll(enumMethods)
         * [values]
         * Compiled from "Explore.java"
         * public final class thinking.enum_type.case2.Explore extends java.lang.Enum<thinking.enum_type.case2.Explore> {
         *   public static final thinking.enum_type.case2.Explore HERE;
         *   public static final thinking.enum_type.case2.Explore THERE;
         *   public static thinking.enum_type.case2.Explore[] values();
         *   public static thinking.enum_type.case2.Explore valueOf(java.lang.String);
         *   static {};
         * }
         *
         * 结果分析：
         * 1）所有的枚举类，都继承了java.lang.Enum抽象类，所以继承了该类中的所有方法。
         *
         * 2）枚举类在编译后，编译器会为其添加values()、valueOf(String)两个方法，可以通过"javap xx.class"看编译后的字节码
         */
    }
}
