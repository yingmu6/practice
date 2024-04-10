package thinking.enum_type.case2;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author chensy
 * @date 2024/4/8
 */
public class Reflection {

    /**
     * 知识点：values()的神秘之处
     */
    public static Set<String> analyze(Class<?> enumClass) {
        System.out.println("------ Analyzing " + enumClass + " ---------");
        System.out.println("Interfaces:");
        for (Type t : enumClass.getGenericInterfaces()) {
            System.out.println(t);
        }
        System.out.println("Base：" + enumClass.getSuperclass());
        System.out.println("Methods：");
        Set<String> methods = new TreeSet<>();
        for (Method m : enumClass.getMethods()) {
            methods.add(m.getName());
        }
        System.out.println(methods);
        return methods;
    }

    public static void main(String[] args) {
        Set<String> exploreMethods = analyze(Explore.class);
        System.out.println("Explore.containsAll(Enum)? " + exploreMethods.containsAll(exploreMethods));
        System.out.println("Explore.removeAll(enumMethods)");
        System.out.println(exploreMethods);
        // OsExecute.command("javap Explore"); 此处报找不到类
    }
}
