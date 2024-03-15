package com.csy.interview.offer_come.design_mode.flyweight;

import lombok.Getter;
import lombok.Setter;

/**
 * @author chensy
 * @date 2024/3/15
 */
@Getter
@Setter
public class Memory {

    private int size;

    private boolean isUsed;

    private String id;

    public Memory(int size, boolean isUsed, String id) {
        this.size = size;
        this.isUsed = isUsed;
        this.id = id;
    }
}
