package relative.basic.event;

import org.junit.Test;

/**
 * @author chensy
 * @date 2022/5/18
 */
public class EventTest {

    /**
     * EventObject/EventListener事件对象与事件监听器概述：
     * a）EventObject：The root class from which all event state objects shall be derived.
     *   （EventObject是所有事件状态对象的根类，它包含了事件源对象）
     * b） A tagging interface that all event listener interfaces must extend.
     *    (EventListener是所有事件监听器接口必须继承的标记接口)
     *
     * 参考链接：
     * a）https://xie.infoq.cn/article/b6b59ed8a6bb913a98c040d15 从观察者模式到 Java 事件处理机制
     *
     */
    @Test
    public void test_basic() {
        System.out.println(11);
    }
}
