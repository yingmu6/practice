package thinking.type_info;

/**
 * @author chensy
 * @date 2024/4/8
 */
public class ToyTest {

    /**
     * 知识点：Class对象
     */

    static class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots {
        FancyToy() {
            super(1);
        }
    }

    static class Toy {
        Toy() {}
        Toy(int i) {}
    }

    interface HasBatteries {
    }

    interface Shoots {
    }

    interface Waterproof {
    }

    static void printInfo(Class cc) {
        System.out.println("Class name：" + cc.getName() +
                " is interface? [" + cc.isInterface() + "]");
        System.out.println("Simple name: " + cc.getSimpleName());
        System.out.println("Canonical name：" + cc.getCanonicalName());
    }

    public static void main(String[] args) {
        Class c = null;
        try {
            c = Class.forName("thinking.type_info.ToyTest.FancyToy");
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find FancyToy");
            System.exit(1);
        }

        printInfo(c);
        for (Class face : c.getInterfaces()) {
            printInfo(face);
        }

        Class up = c.getSuperclass();
        Object obj = null;
        try {
            obj = up.newInstance();
        } catch (InstantiationException e) {
            System.out.println("Cannot instantiate");
            System.exit(1);
        } catch (IllegalAccessException e) {
            System.out.println("Cannot access");
            System.exit(1);
        }
        printInfo(obj.getClass());
    }
}
