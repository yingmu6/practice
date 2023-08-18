package com.csy.design.observer.v1;

import lombok.Getter;
import lombok.Setter;

/**
 * @author chensy
 * @date 2023/8/18
 */
@Getter
@Setter
public class NewsChannel implements Channel{
    private String news;
    @Override
    public void update(Object news) {
        this.setNews((String) news);
    }
}
