package com.csy.design.observer;

import com.csy.design.observer.v1.NewsAgency;
import com.csy.design.observer.v1.NewsChannel;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author chensy
 * @date 2023/8/18
 */
public class ObserverTest {
    /**
     * 观察着模式_测试
     * 1）Observer is a behavioral design pattern. It specifies communication（沟通、通信） between objects: observable and observers.
     * An observable（可观察对象） is an object which notifies observers about the changes in its state
     *
     * 2）When the change happens, The observable notifies the observers，To be able to do that, the observable object needs to keep references to the observers
     *
     * 3）The java.util.Observer interface defines the update() method, so there's no need to define it ourselves, as we did in the previous section.
     *
     * 参考链接：
     * a）https://www.baeldung.com/java-observer-pattern
     */

    /**
     * 场景1：自定义可观察者对象、观察者对象
     */
    @Test
    public void test_basic() {
        NewsAgency newsAgency = new NewsAgency();

        NewsChannel channel1 = new NewsChannel();
        NewsChannel channel2 = new NewsChannel();

        newsAgency.addObserver(channel1);
        newsAgency.addObserver(channel2);

        newsAgency.setNews("hello");
        Assert.assertEquals("hello", channel1.getNews());
        Assert.assertEquals("hello", channel2.getNews());

        /**
         * 输出结果：
         *
         * 结果分析：
         * 可观察者的状态改变，就会通知所有的观察，执行相关的逻辑
         */
    }

    /**
     * 场景2：使用java提供的Observer来实现
     */
    @Test
    public void test_observer_from_java() {

    }
}
