package relative.basic.object;

/**
 * 对象拷贝
 * @author : chensy
 * Date : 2020-03-07 23:55
 */
public class CopyObject {
    public static void main(String[] args) {
        //basicUse();
        //shallow();
        //deepV1();
        deepV2();
    }

    /**
     * 普通赋值，指向同一块内存地址，改变值相互影响
     */
    public static void basicUse() {
        Student s1 = new Student();
        s1.setName("张三");
        Student s2 = s1;
        System.out.println("未改变前：" + s2.getName() + "," + s1.getName());
        s1.setName("张三3");
        System.out.println("改变后：" + s2.getName() + "," + s1.getName());

    }

    /**
     * 浅复制：对象中的属性都是基本类型
     */
    public static void shallow() {
        StudentOfShallow shallowA = new StudentOfShallow();
        shallowA.setName("李四");

        StudentOfShallow shallowB = (StudentOfShallow) shallowA.clone();
        System.out.println("未改变前：" + shallowA.getName() + "," + shallowB.getName());

        shallowA.setName("李四4");
        System.out.println("改变后：" + shallowA.getName() + "," + shallowB.getName());

    }

    /**
     * 输出：
     * 基本类型未改变前：王五,王五
     * 基本类型未改变后：王五,王五5
     * 引用类型改变后：杭州22,杭州22
     *
     * Address没有重写clone方法
     * 若对象中的属性包含对象，并且没有重写clone() 方法，也相当与浅复制，属性对象变更，会引起另外对象的变更
     */
    public static void deepV1() {
        StudentOfDeep deepA = new StudentOfDeep();
        deepA.setName("王五");
        Address address = new Address();
        address.setAddressName("杭州");
        deepA.setAddress(address);

        StudentOfDeep deepB = (StudentOfDeep) deepA.clone();
        System.out.println("基本类型未改变前：" + deepA.getName() + "," + deepB.getName());

        deepB.setName("王五5");
        System.out.println("基本类型未改变后：" + deepA.getName() + "," + deepB.getName());

        address.setAddressName("杭州22");
        System.out.println("引用类型改变后：" + deepA.getAddress().getAddressName() + "," + deepB.getAddress().getAddressName());

    }

    /**
     * 深复制：对象中的基本属性、对象属性都互不影响
     */
    public static void deepV2() {
        StudentOfDeepV2 deepA = new StudentOfDeepV2();
        deepA.setName("王五");
        AddressV2 addressV2 = new AddressV2();
        addressV2.setAddressName("北京");
        deepA.setAddressV2(addressV2);

        StudentOfDeepV2 deepB = (StudentOfDeepV2) deepA.clone();
        System.out.println("基本类型未改变前：" + deepA.getName() + "," + deepB.getName());

        deepB.setName("王五5");
        System.out.println("基本类型未改变后：" + deepA.getName() + "," + deepB.getName());

        addressV2.setAddressName("北京22");
        System.out.println("引用类型改变后：" + deepA.getAddressV2().getAddressName() + "," + deepB.getAddressV2().getAddressName());

    }

}
