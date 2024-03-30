package thinking.inner_class.case4;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class Z extends D {
    E makeE() {
        return new E() {}; //返回匿名实现类
    }
}
