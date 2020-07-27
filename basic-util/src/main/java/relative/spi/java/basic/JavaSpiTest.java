package relative.spi.java.basic;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * SPI全称Service Provider Interface，是Java提供的一套用来被第三方实现或者扩展的API，它可以用来启用框架扩展和替换组件。
 * Java SPI 实际上是“基于接口的编程＋策略模式＋配置文件”组合实现的动态加载机制。是JDK内置的一种服务提供发现机制。SPI是一种动态替换发现的机制，
 * 适用于：调用者根据实际使用需要，启用、扩展、或者替换框架的实现策略  https://juejin.im/post/5b9b1c115188255c5e66d18c
 *
 * @author : chensy
 * Date : 2020-03-12 14:03
 */
public class JavaSpiTest {
    public static void main(String[] args) {
        ServiceLoader<Animal> loader = ServiceLoader.load(Animal.class);
        // 接口可能有多个实现类，所以要循环遍历
        Iterator it = loader.iterator();
        while (it.hasNext()) {
            Animal animal = (Animal) it.next();
            animal.cry();
        }

    }
}
