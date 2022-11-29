package com.csy.design.factory.method;

/**
 * @author : chensy
 * Date : 2020/11/17 下午2:39
 */
public class TvProductFactory implements ProductFactory {
    @Override
    public Product product() {
        return new Tv();
    }
}
