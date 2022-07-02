package relative.basic.abstracttest;

/**
 * @author : chensy
 * Date : 2020/8/24 上午10:07
 */
public class Pig extends Animal{
    @Override
    void cry() { //此处会默认调用super()，调用父类的构造函数
        System.out.println("猪叫");
    }
}
