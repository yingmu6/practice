package com.csy.design.strategy;

/**
 * @Author chenSy
 * @Date 2023/04/24 13:42
 * @Description
 */
public class Item { //购买的商品
    private String upcCode;
    private int price;

    public Item (String upc, int cost){
        this.upcCode = upc;
        this.price = cost;
    }

    public String getUpcCode() {
        return upcCode;
    }

    public int getPrice() {
        return price;
    }
}
