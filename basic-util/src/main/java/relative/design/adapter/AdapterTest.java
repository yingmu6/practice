package relative.design.adapter;

import java.util.concurrent.Callable;

/**
 * @author : chensy
 * Date : 2020/10/19 下午10:52
 */
public class AdapterTest {
    public static void main(String[] args) {
        Callable callable = new Task();
        //Thread thread = new Thread(callable); 需要适配转换，Thread接收是Runnable接口，而不是Callable接口
        //在不改变原来接口的情况下，通过适配器转换为另一个希望的接口
        Thread thread = new Thread(new TaskAdapter(callable));
        thread.start();
    }
}

/**
 * 适配器模式是Adapter，也称Wrapper
 * 将一个类的接口转换成客户希望的另外一个接口，使得原本由于接口不兼容而不能一起工作的那些类可以一起工作
 * https://www.liaoxuefeng.com/wiki/1252599548343744/1281319245971489
 *
 * https://www.runoob.com/design-pattern/adapter-pattern.html   适配器模式
 */
