package com.csy.design.proxy;

import com.csy.design.proxy.cglib.CglibProxyFactory;
import com.csy.design.proxy.cglib.TicketBuy;
import com.csy.design.proxy.dynamic.ProxyFactory;
import com.csy.design.proxy.jdk_proxy.HelloProxyImpl;
import com.csy.design.proxy.jdk_proxy.IHelloProxy;
import com.csy.design.proxy.jdk_proxy.MyInvocationHandler;
import com.csy.design.proxy.jdk_proxy.MyInvocationHandler2;
import com.csy.design.proxy.static_p.ITicket;
import com.csy.design.proxy.static_p.TrainStation;
import com.csy.design.proxy.static_p.TrainStationAgent;
import org.junit.Assert;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * 代理模式测试
 * @author : chensy
 * Date : 2020-03-11 14:31
 */
public class ProxyTest {

    /**
     * 代理模式_测试
     *
     * 参考链接：
     * a）https://refactoring.guru/design-patterns/proxy
     * b）https://www.runoob.com/design-pattern/proxy-pattern.html 菜鸟教程
     */

    /**
     * 场景1：静态代理：
     * 优点：不改变目标对象，就可以对目标对象进行访问控制，功能增强
     * 缺点：1）需要与目标对象实现相同的接口，接口改变，代理对象也要改变
     *      2）只能代理一种类型的对象，多种类型需要再建另外的代理对象
     *
     * 具体使用流程：
     * 1）定义代理类，与目标类实现相同的接口
     * 2）代理类持有目标类的引用，可通过构造方法传入
     * 3）代理类的方法内，调用目标对象的相同方法（）
     */
    @Test
    public void staticProxy() {
        // 直接访问目标对象
        ITicket ticket = new TrainStation();
        System.out.println(ticket.getTicket(150));

        // 通过代理对象访问目标对象
        ITicket target = new TrainStation(); //目标对象
        ITicket proxy = new TrainStationAgent(target); //将目标对象委托给代理对象
        System.out.println(proxy.getTicket(150));
    }

    /**
     * 场景2：动态代理：
     * 优点：不用写很多的代理类，运行时动态生成
     * 缺点：要求目标对象必须实现接口
     */
    @Test
    public void dynamicProxy() {
        ITicket ticket = new TrainStation();
        ITicket proxy = (ITicket) new ProxyFactory(ticket).getProxyInstance();
        System.out.println(proxy.getTicket(150)); //代理对象的方法被调用时，会进入到InvocationHandler的invoke方法中，将增强逻辑放在该方法中
    }

    /**
     * 场景3：cglib使用
     */
    @Test
    public void cglibProxy() {
        TicketBuy ticketBuy = new TicketBuy();
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory(ticketBuy);
        // 直接转换为目标对象，不需要目标对象实现接口
        TicketBuy proxyInstance = (TicketBuy)cglibProxyFactory.getProxyInstance();
        System.out.println(proxyInstance.getTicket(100));
    }

    /**
     * 场景4：JDK代理使用
     * 1）代理对象产生的步骤：
     *    a）Class<?> cl = getProxyClass0(loader, intfs); 从缓存WeakCache中获取代理类，若缓存中不存在，则通过代理工厂ProxyClassFactory产生代理类
     *    b）final Constructor<?> cons = cl.getConstructor(constructorParams); 获取代理类带有InvocationHandler参数的构造器Constructor
     *    c）return cons.newInstance(new Object[]{h}); 创建代理类的实例对象并返回
     *
     * 2）代理类产生的步骤：即getProxyClass0(...)
     *    a）ProxyClassFactory#apply使用代理Class工厂产生类
     *    b）做前置校验，如是否能够通过当前加载器加载Class、Class是否为接口、判断接口是否可访问等
     *    c）ProxyGenerator.generateProxyClass产生代理类Class的字节数组
     *    d）Proxy#defineClass0：将字节数组通过native方法得到代理类的Class
     *
     * 3）CGLIB与JDK动态代理区别
     *    a）JDK代理只能对实现接口的类生成代理；CGLib是针对类实现代理
     *    b）JDK代理使用的是反射机制实现aop的动态代理，CGLib代理使用字节码处理框架ASM
     *    c）JDK动态代理机制是委托机制，CGLib则使用的继承机制
     *
     * 问题点：
     * 1）jdk动态代理能否为实现类创建代理，还是只能为接口？
     *    解答：不能为类创建代理，在创建代理类时ProxyClassFactory#apply会检查是否是接口
     *
     * 2）怎样通过工具，如arthas查看jdk产生的代理对象的源码？
     *    解答：方式1：可以通过test_jdk_dynamic_proxy_v2中的输出字节码文件观看
     *
     *
     * 参考链接：
     * a）https://www.baeldung.com/java-dynamic-proxies
     * b）https://zhuanlan.zhihu.com/p/93949583  JDK动态代理底层源码分析
     * c）https://www.cnblogs.com/zjfjava/p/13919437.html CGLIB与JDK动态代理区别
     */
    @Test
    public void test_jdk_dynamic_proxy_v1() {
        Map mapProxyInstance = (Map) Proxy.newProxyInstance(
                ProxyTest.class.getClassLoader(), new Class[] { Map.class },
                new MyInvocationHandler(new HashMap<>()));

        mapProxyInstance.put("hello", "world"); //为接口创建代理对象后，就可以直接操作接口中的方法了（使用向上转型）

        mapProxyInstance.get("hello");

        CharSequence csProxyInstance = (CharSequence) Proxy.newProxyInstance(
                ProxyTest.class.getClassLoader(),
                new Class[] { CharSequence.class },
                new MyInvocationHandler("Hello World"));

        csProxyInstance.charAt(0);
        csProxyInstance.length();

        /**
         * 输出结果：
         * 执行方法：put，结果值：null
         * 执行方法：get，结果值：world
         * 执行方法：charAt，结果值：H
         * 执行方法：length，结果值：11
         *
         * 结果分析：
         * 1）耗费时间在每次运行不同，但结果值每次运行结果一致
         * 2）创建代理后，使用操作接口操作方式时，会进入InvocationHandler实现类的invoke方法中，所以具体逻辑看这里面
         */
    }

    @Test
    public void test_jdk_dynamic_proxy_v2() throws IOException {
//       IHelloProxy helloProxy = (IHelloProxy) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(),
//               new Class[]{IHelloProxy.class}, new MyInvocationHandler2(new IHelloProxy() { //此处若使用匿名内部类，会抛出"java.lang.IllegalAccessException"异常，访问权限异常
//                   private String proxyName;
//                   @Override
//                   public String getProxyName(String proxyName) {
//                       return proxyName;
//                   }
//
//                   @Override
//                   public void setProxyName(String proxyName) {
//                       this.proxyName = proxyName;
//                   }
//               }));

        IHelloProxy helloProxy = (IHelloProxy) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(),
                new Class[]{IHelloProxy.class}, new MyInvocationHandler(new HelloProxyImpl())); //要指定接口的实现类，在回调处理器的invoke方式时，调用目标类对应方法

       helloProxy.setProxyName("hello");

       String proxyName = helloProxy.getProxyName("hello");
       System.out.println("结果值：" + proxyName);


       // 输出动态代理对象对应的字节码文件（打开即可用） 从产生的字节码中可以看出，构造代理时会传入InvocationHandler实例对象，然后再调用代理接口方法时，会调用InvocationHandler的invoke方法
//        byte[] bytes = ProxyGenerator.generateProxyClass("MyProxy", new Class[]{IHelloProxy.class});
//        File file = new File("/Users/shengyong.chen/self_pro/practice/practice-sj/src/main/java/com/csy/design/proxy/myProxy.class");
//        FileOutputStream outputStream = new FileOutputStream(file);
//        outputStream.write(bytes);

        /**
         * 结果输出：
         *
         * 执行方法：setProxyName，结果值：null
         * 执行方法：getProxyName，结果值：proxy_hello
         * 结果值：proxy_hello
         *
         * 结果分析：
         * 1）在创建代理对象时，指定代理的接口IHelloProxy，以及调用处理器MyInvocationHandler2，调用处理器指定了接口实现类，即目标对象
         * 2）在调用接口方法时，即调用调用代理对象的方法，会回调InvocationHandler中的invoke方法，可在该方法中做增强处理
         *
         * 问题点答疑：
         * 1）为啥MyInvocationHandler2使用method.invoke，会一直调用setProxyName方法，停不下来？
         *    解答：通过debug调试来看是，是回调处理器中invoke方法时，调用method.invoke(proxy, args)，在代理对象中递归调用代理对象的方法，且没有递归出口，所以死循环了。
         *         所以应该在处理器的invoke方法中调用目标对象的方法，可以在调用前，调用后做增强处理（需要维护接口实例类的对象，即target对象）
         */
    }

    @Test
    public void test_jdk_dynamic_proxy_v3() { //测试jdk是否能为类创建代理

        try {
            HelloProxyImpl helloProxy = (HelloProxyImpl) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(),
                    new Class[]{HelloProxyImpl.class}, new MyInvocationHandler2(new HelloProxyImpl()));

            helloProxy.setProxyName("hello");

            String proxyName = helloProxy.getProxyName("hello");
            System.out.println("结果值：" + proxyName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /**
         * 输出结果：
         * com.csy.design.proxy.jdk_proxy.HelloProxyImpl is not an interface
         *
         * 结果分析：
         * jdk不能为类创建代理，只能为接口，在代理工厂创建代理类时会做检查ProxyClassFactory#apply是否是接口
         */

        //-------调试输出模版--------

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         * 结果概括：
         */

        //-------内容概要模版--------

        /**
         * 知识点：xxx
         *
         * 知识点概括：
         *
         * 问题点答疑：
         *
         */
    }

}
