package basic.unit.boot;

import org.springframework.stereotype.Service;

/**
 * @author chensy
 * @date 2022/3/4
 */
@Service("helloService")
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String msg) {
        System.out.println("hello world!!");
        return "hello:" + msg;
    }
}
