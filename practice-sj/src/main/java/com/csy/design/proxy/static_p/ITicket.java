package com.csy.design.proxy.static_p;

import java.io.Serializable;

/**
 * @author : chensy
 * Date : 2020/11/11 上午10:01
 */
public interface ITicket extends Serializable {
    Integer getTicket(double price);
}