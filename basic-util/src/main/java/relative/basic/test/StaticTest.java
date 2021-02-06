package relative.basic.test;
/**
 * @author chensy
 * @date 2021/2/1
 */
public class StaticTest {
    private static StaticTest totalTest = new StaticTest();

    public static void main(String[] args) {
        // 虽然是静态方法，但每调用一次都会new对象，所以对象是不同的
        StaticTest test = StaticTest.getInstance();
        StaticTest test1 = StaticTest.getInstance();
        System.out.println(test + "," + test1);
        // 输出 relative.basic.test.StaticTest@6b884d57,relative.basic.test.StaticTest@38af3868

        // 引用的是静态类成员变量，所有对象访问到的值相同
        StaticTest test2 = new StaticTest();
        StaticTest temp2 = test2.getTotalTest();

        StaticTest test3 = new StaticTest();
        StaticTest temp3 = test3.getTotalTest();

        System.out.println(temp2 + "," + temp3);
        // 输出 relative.basic.test.StaticTest@77459877,relative.basic.test.StaticTest@77459877
    }

    public static StaticTest getInstance() {
        return new StaticTest();
    }

    public static StaticTest createInstance() {
        return totalTest;
    }

    public  StaticTest getTotalTest() {
        return totalTest;
    }

    public  void setTotalTest(StaticTest totalTest) {
        StaticTest.totalTest = totalTest;
    }
}
