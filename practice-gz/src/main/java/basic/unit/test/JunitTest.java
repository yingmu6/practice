package basic.unit.test;

import org.junit.*;

/**
 * @author chensy
 * @date 2022/3/3
 */
public class JunitTest { //运行类，可以执行多个测试方法

    @BeforeClass
    public static void init() { //所有方法调用前被执行，方法被static修饰，全局只执行一次，且第一个执行
        System.out.println("init");
    }

    @AfterClass
    public static void destroy() { //所有方法调用后被执行，方法被static修饰，全局只执行一次，且最后一个执行
        System.out.println("destroy");
    }

    @Before
    public void beforeMethod() { //每个方法调用前被执行，每调用一个方法，就执行一次，类中有多个测试方法时，会被执行多次
        System.out.println("before Method");
    }

    @After
    public void AfterMethod() { //每个方法调用后被执行，每调用一个方法，就执行一次，类中有多个测试方法时，会被执行多次
        System.out.println("after Method");
    }

    @Test
    public void sayHello() { //@Test修饰的方法，要求是public void
        System.out.println("hello world!");
    }

    @Test
    public void sayHello2() {
        sayHello();
        System.out.println("hello world2!");
    }

    @Test(expected = SelfExceptionClass.class)
    public void sayHelloWithException() { //expected指定抛出的异常类型
        String msg = "hi";
        String msg2 = "hello";
        Assert.assertEquals(msg, msg2);
    }
}

class SelfExceptionClass extends Throwable {
    SelfExceptionClass() {
        super("自定义异常类");
    }
}
