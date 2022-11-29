package com.csy.design.defult;

/**
 * 默认适配器，实现了接口中所有方法，子类可以选择实现执行的方法
 * https://www.cnblogs.com/java-my-life/archive/2012/04/13/2442795.html
 * https://design-patterns.readthedocs.io/zh_CN/latest/structural_patterns/adapter.html
 *
 * @author : chensy
 * Date : 2020-03-01 12:12
 */
public class MediaPlayAdapter implements MediaPlay {
    @Override
    public void playMp3() {

    }

    @Override
    public void playVideo() {

    }
}
