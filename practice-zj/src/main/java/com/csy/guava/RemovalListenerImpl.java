package com.csy.guava;

import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

/**
 * @Author chenSy
 * @Date 2023/03/28 15:23
 * @Description
 */
public class RemovalListenerImpl implements RemovalListener<String, String> {

    @Override
    public void onRemoval(RemovalNotification<String, String> removalNotification) {
        System.out.println("有缓存被移除了：key = " + removalNotification.getKey() + ", value=" + removalNotification.getValue());
    }
}
