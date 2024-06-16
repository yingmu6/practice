package com.csy.spring.ioc;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author chenSy
 * @Date 2023/05/13 16:09
 * @Description
 */
@Setter
@Getter
public class ItemImpl2 implements Item {

    private String name;

    private int num;

    @Override
    public String printItem() {
        return "条目2：name = " + this.getName() + ", num = " + this.getNum();
    }
}
