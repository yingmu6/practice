package relative.rmi.provider;

import relative.rmi.api.HelloService;

/**
 * @author chensy
 * @date 2022/4/25
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("你好！");
    }
}
