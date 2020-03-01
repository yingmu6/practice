package relative.design.basic;

/**
 * 被适配者，含有特殊实现，不能直接用通用接口，需要使用适配器类，来做转化
 * @author : chensy
 * Date : 2020-03-01 14:43
 */
public class Adaptee {
    public void saySepecalHello(String lang) {
        // 特殊实现
        if ("zh".equals(lang)) {
            System.out.println("你好！");
        } else {
            System.out.println("hello!");
        }
    }
}
