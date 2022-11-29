package spring.aop_copy;

/**
 * @author : chensy
 * https://www.jianshu.com/p/994027425b44 参考网址
 * Date : 2020-03-11 14:51
 */
import org.springframework.stereotype.Component;

@Component("landlord")
public class Landlord { // 房东（目标类）

    public void service() {
        // 仅仅只是实现了核心的业务功能
        System.out.println("签合同");
        System.out.println("收房租");
    }
}