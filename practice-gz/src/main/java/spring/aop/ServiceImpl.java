package spring.aop;

/**
 * @author : chensy
 * Date : 2020-03-11 14:42
 */
public class ServiceImpl implements Service {
    @Override
    public void say() {
        System.out.println("你好！");
    }
}
