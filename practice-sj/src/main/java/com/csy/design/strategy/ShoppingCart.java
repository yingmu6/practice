package com.csy.design.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author chenSy
 * @Date 2023/04/24 13:42
 * @Description
 */
public class ShoppingCart { //购物车
    //List of items
    List<Item> items; //购物车中的商品列表

    public ShoppingCart(){
        this.items = new ArrayList<Item>();
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public void removeItem(Item item){
        this.items.remove(item);
    }

    public int calculateTotal(){
        int sum = 0;
        for(Item item : items){
            sum += item.getPrice();
        }
        return sum;
    }

    public void pay(PaymentStrategy paymentMethod){ //行为对应了多种算法实现
        int amount = calculateTotal();
        paymentMethod.pay(amount);
    }
}
