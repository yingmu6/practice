package com.basic.use;

/**
 * @author : chensy
 * Date : 2020/11/13 下午1:04
 */
public class HelloServiceStub implements HelloService{
    private HelloService helloService;

    /**
     * checkStubAndMock(Class<?> interfaceClass) 必须要有Stub必须有可传入 Proxy 的构造函数
     * java.lang.IllegalStateException: No such constructor "public HelloServiceStub(com.basic.use.HelloService)"
     */
    public HelloServiceStub(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("参数不正确");
        }
        try {
            helloService.sayHello(str); //本地存根处理后，可以选择不调用选择服务
        } catch (Exception e) {
            // 调用失败，可做容错处理
            System.out.println("出现异常数据");
        }
        return "出现异常数据";
    }

    /**
     * 远程服务后，客户端通常只剩下接口，而实现全在服务器端，但提供方有些时候想在客户端也执行部分逻辑，
     * 比如：做 ThreadLocal 缓存，提前验证参数，调用失败后伪造容错数据等等，此时就需要在 API 中带上 Stub，
     * 客户端生成 Proxy 实例，会把 Proxy 通过构造函数传给 Stub，然后把 Stub 暴露给用户，Stub 可以决定要不要去调 Proxy。
     */
}
